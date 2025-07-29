package com.sanwenyukaochi.security.service;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.http.HttpStatus;
import com.sanwenyukaochi.security.annotation.DataScope;
import com.sanwenyukaochi.security.bo.VideoSliceBO;
import com.sanwenyukaochi.security.bo.VideoSliceCallbackBO;
import com.sanwenyukaochi.security.constant.FileConstants;
import com.sanwenyukaochi.security.bo.VideoBO;
import com.sanwenyukaochi.security.dto.*;
import com.sanwenyukaochi.security.entity.Clip;
import com.sanwenyukaochi.security.entity.ClipGroup;
import com.sanwenyukaochi.security.entity.Tag;
import com.sanwenyukaochi.security.entity.Task;
import com.sanwenyukaochi.security.entity.Video;
import com.sanwenyukaochi.security.entity.relation.ClipTag;
import com.sanwenyukaochi.security.enums.TaskStatus;
import com.sanwenyukaochi.security.enums.TaskType;
import com.sanwenyukaochi.security.exception.APIException;
import com.sanwenyukaochi.security.repository.*;
import com.sanwenyukaochi.security.repository.ClipTagRepository;
import com.sanwenyukaochi.security.repository.TagRepository;
import com.sanwenyukaochi.security.security.service.UserDetailsImpl;
import com.sanwenyukaochi.security.storage.FileStorage;
import com.sanwenyukaochi.security.utils.FfmpegUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import cn.hutool.core.io.FileUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;
    private final TaskRepository taskRepository;
    private final ClipGroupRepository clipGroupRepository;
    private final ClipRepository clipRepository;
    private final TagRepository tagRepository;
    private final ClipTagRepository clipTagRepository;
    private final FileStorage fileStorage;
    private final Snowflake snowflake;
    private final WebClient webClient;
    @Value("${storage.sfs.local-dir}")
    private String localDir;
    @Value("${agent.video-slice.callback-url}")
    private String callbackUrl;

    @DataScope
    public Page<VideoDTO> findAllVideo(VideoBO newVideoBO, Pageable pageable) {
        Page<Video> all = newVideoBO.getHasClips() == null ? videoRepository.findAll(pageable) : videoRepository.findAllByHasClips(newVideoBO.getHasClips(), pageable);
        return all.map(newVideo -> new VideoDTO(
                newVideo.getId(),
                newVideo.getFullFileNameWithName(),
                newVideo.getFileSize(),
                newVideo.getDuration(),
                newVideo.getVideoPath(),
                newVideo.getCoverImage()
        ));
    }

    @Transactional
    @SneakyThrows
    public VideoDTO uploadVideo(VideoBO videoBO, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Video newVideo = new Video();
        newVideo.setId(snowflake.nextId());
        newVideo.setFileName(videoBO.getFileName());
        newVideo.setFileExt(videoBO.getFileExt());
        // 构建新视频对象路径
        String renamedObjectPath = String.format("%s/%s/%s/%s.%s", userDetails.getTenant().getId(), userDetails.getId(), newVideo.getId(), newVideo.getId(), videoBO.getFileExt());
        newVideo.setVideoPath(String.format("%s/%s", fileStorage.getBucketPath(), renamedObjectPath));
        // 构建封面图路径
        String coverObjectPath = String.format("%s/%s/%s/%s/%s.%s", userDetails.getTenant().getId(), userDetails.getId(), newVideo.getId(), FileConstants.COVER_DIR_NAME, newVideo.getId(), FileConstants.EXT_JPG);
        newVideo.setCoverImage(String.format("%s/%s", fileStorage.getBucketPath(), coverObjectPath));
        // 修改 OBS 上视频对象名
        String originalObjectPath = String.format("%s/%s/%s.%s", userDetails.getTenant().getId(), userDetails.getId(), videoBO.getFileName(), videoBO.getFileExt());
        fileStorage.renameObject(originalObjectPath, renamedObjectPath);
        // 下载 OBS 视频到本地
        Path videoObjectPath = Paths.get(renamedObjectPath);
        Path localVideoPath = Paths.get(localDir).resolve(videoObjectPath);
        FileUtil.mkdir(localVideoPath.getParent().toFile());
        if (!FileUtil.exist(localVideoPath.toFile())) fileStorage.downloadFileByCheckpoint(videoObjectPath.toString(), localVideoPath.toString());
        // 生成封面图
        Path localCoverPath = Paths.get(localDir, coverObjectPath);
        if (localCoverPath.getParent() != null && Files.notExists(localCoverPath.getParent())) Files.createDirectories(localCoverPath.getParent());
        FfmpegUtils.getFirstImageFromVideo(localVideoPath.toString(), "00:00:00.000", localCoverPath.toString());
        // 上传封面图至 OBS
        fileStorage.uploadFileByFileStream(coverObjectPath, localCoverPath.toString());
        newVideo.setFileSize(new File(localVideoPath.toString()).length());
        newVideo.setDuration(FfmpegUtils.getVideoDuration(localVideoPath.toString()));
        newVideo.setTenantId(userDetails.getTenant().getId());
        videoRepository.save(newVideo);
        return new VideoDTO(
                newVideo.getId(),
                newVideo.getFullFileNameWithName(),
                newVideo.getFileSize(),
                newVideo.getDuration(),
                newVideo.getVideoPath(),
                newVideo.getCoverImage()
        );
    }

    @Transactional
    public void deleteVideo(VideoBO videoBO, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Video dbVideo = videoRepository.findById(videoBO.getId()).orElseThrow(() -> new APIException(HttpStatus.HTTP_NOT_FOUND, "视频不存在"));
        String renamedObjectPath = String.format("%s/%s/%s", userDetails.getTenant().getId(), userDetails.getId(), videoBO.getId());
        fileStorage.deleteObject(String.format(renamedObjectPath));
        Path localVideoFolderPath = Paths.get(localDir, renamedObjectPath);
        if (FileUtil.exist(localVideoFolderPath.toFile())) FileUtil.del(localVideoFolderPath);
        videoRepository.delete(dbVideo);
    }

    @Transactional
    public VideoDTO updateVideo(VideoBO videoBO) {
        Video dbVideo = videoRepository.findById(videoBO.getId()).orElseThrow(() -> new APIException(HttpStatus.HTTP_NOT_FOUND, "视频不存在"));
        dbVideo.setFileName(videoBO.getFileName());
        dbVideo.setFileExt(videoBO.getFileExt());
        videoRepository.save(dbVideo);
        return new VideoDTO(
                dbVideo.getId(),
                dbVideo.getFullFileNameWithName(),
                dbVideo.getFileSize(),
                dbVideo.getDuration(),
                dbVideo.getVideoPath(),
                dbVideo.getCoverImage()
        );
    }

    @Transactional
    public String createVideoSlice(VideoSliceBO videoSliceBO, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Task task = new Task();
        task.setId(snowflake.nextId());
        task.setType(TaskType.SLICE);
        task.setStatus(TaskStatus.QUEUED);
        task.setTenantId(userDetails.getTenant().getId());
        taskRepository.save(task);
        // TODO 未来会考虑后端与后端之间双向通信
        try {
            Video dbVideo = videoRepository.findById(videoSliceBO.getId()).orElseThrow(IllegalArgumentException::new);
            callAgent(videoSliceBO, dbVideo, task.getId());
        } catch (IllegalArgumentException ex) {
            return "视频不存在";
        } catch (WebClientRequestException ex) {
            task.setStatus(TaskStatus.FAILED);
            taskRepository.save(task);
            return "Agent连接失败";
        } catch (Exception e) {
            task.setStatus(TaskStatus.FAILED);
            taskRepository.save(task);
            return "Agent异常";
        }
        return "任务创建成功";
    }

    private void callAgent(VideoSliceBO videoSliceBO, Video dbVideo, Long taskId) {
        webClient.post()
                .uri("/api/v1/allinone")
                .bodyValue(new AgentVideoSliceDTO(
                        dbVideo.getFullFileNameWithId(),
                        dbVideo.getVideoPath().replace(fileStorage.getBucketPath(), localDir),
                        videoSliceBO.getTaskType(),
                        videoSliceBO.getVideoType(),
                        callbackUrl,
                        taskId,
                        videoSliceBO.getAdaptiveThreshold(),
                        videoSliceBO.getAddSubtitle()
                ))
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(), response ->
                        response.bodyToMono(String.class).doOnNext(body -> {
                            log.error("Agent 返回非成功状态: {}, 响应体: {}", response.statusCode(), body);
                        }).then(Mono.empty())
                )
                .bodyToMono(Void.class)
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(3))
                        .filter(e -> e instanceof RuntimeException)
                        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> retrySignal.failure())
                )
                .block();
    }

    @Transactional
    public void handleClipVideoCallbackAsync(VideoSliceCallbackBO videoSliceCallbackBO) {
        Task task = taskRepository.findById(videoSliceCallbackBO.getTaskId()).orElseThrow(() -> new APIException(HttpStatus.HTTP_NOT_FOUND,"任务不存在"));
        try {
            Video video = videoRepository.findById(videoSliceCallbackBO.getVideoId()).orElseThrow(() -> new APIException(HttpStatus.HTTP_NOT_FOUND, "视频不存在"));
            if (videoSliceCallbackBO.getClipGroups().isEmpty()) {
                log.error("任务Id：{}，生成视频大纲失败：Python返回Null", task.getId());
                task.setStatus(TaskStatus.FAILED);
                taskRepository.save(task);
                return;
            }
            video.setHasClips(true);
            video.setHasOutline(true);
            Map<String, Tag> tagMap = tagRepository.findAll().stream().collect(Collectors.toMap(tag -> tag.getName() + "_" + tag.getType(), Function.identity()));
            ClipBuildResult result = buildClipStructure(video, task, videoSliceCallbackBO, tagMap);
            task.setStatus(TaskStatus.FINISHED);
            batchSave(result.clipGroups(), clipGroupRepository::saveAll);
            batchSave(result.clips(), clipRepository::saveAll);
            batchSave(result.clipTags(), clipTagRepository::saveAll);
            videoRepository.save(video);
            taskRepository.save(task);
            log.info("任务Id：{}, 视频Id：{}, 成功保存 {} 个组，{} 个剪辑，{} 个标签",
                    task.getId(), video.getId(),
                    result.clipGroups().size(), result.clips().size(), result.clipTags().size());
        } catch (Exception e) {
            log.error("处理剪辑回调失败，任务Id：{}, 错误：{}", task.getId(), e.getMessage(), e);
            task.setStatus(TaskStatus.FAILED);
            taskRepository.save(task);
            throw e;
        }
    }
    private record ClipBuildResult(
            List<ClipGroup> clipGroups,
            List<Clip> clips,
            List<ClipTag> clipTags
    ) {}
    private ClipBuildResult buildClipStructure(Video video, Task task, VideoSliceCallbackBO bo, Map<String, Tag> tagMap) {
        List<ClipGroup> groups = new ArrayList<>();
        List<Clip> clips = new ArrayList<>();
        List<ClipTag> tags = new ArrayList<>();
        int groupOrder = 0;
        for (VideoSliceCallbackBO.VideoClipGroupBO groupBO : bo.getClipGroups()) {
            ClipGroup group = new ClipGroup();
            group.setId(snowflake.nextId());
            group.setVideo(video);
            group.setSummary(groupBO.getSummary());
            group.setStart(groupBO.getStart());
            group.setEnd(groupBO.getEnd());
            group.setGroupOrder(groupOrder++);
            group.setTenantId(task.getTenantId());
            int clipOrder = 0;
            for (VideoSliceCallbackBO.VideoClipBO clipBO : groupBO.getClips()) {
                Clip clip = buildClip(clipBO, clipOrder++, group, task, bo);
                clips.add(clip);
                group.getClips().add(clip);
                if (StringUtils.hasText(clipBO.getTopic()) && StringUtils.hasText(clipBO.getType())) {
                    Tag tag = getOrCreateTagFromMap(tagMap, clipBO.getTopic(), clipBO.getType(), task.getTenantId());
                    ClipTag clipTag = buildClipTag(clip, tag, task);
                    tags.add(clipTag);
                }
            }
            video.getClipGroups().add(group);
            groups.add(group);
        }
        return new ClipBuildResult(groups, clips, tags);
    }
    
    private Clip buildClip(VideoSliceCallbackBO.VideoClipBO clipBO, int order, ClipGroup group, Task task, VideoSliceCallbackBO callbackBO) {
        Clip clip = new Clip();
        clip.setId(snowflake.nextId());
        clip.setStart(clipBO.getStart());
        clip.setEnd(clipBO.getEnd());
        clip.setOrderInGroup(order);
        clip.setClipGroup(group);
        clip.setTenantId(task.getTenantId());
        if (callbackBO.isAddSubtitle() && clipBO.getSubtitles() != null) {
            clip.setSubtitles(clipBO.getSubtitles());
        }
        return clip;
    }

    private ClipTag buildClipTag(Clip clip, Tag tag, Task task) {
        ClipTag clipTag = new ClipTag();
        clipTag.setId(snowflake.nextId());
        clipTag.setClip(clip);
        clipTag.setTag(tag);
        clipTag.setTenantId(task.getTenantId());
        return clipTag;
    }
    
    private Tag getOrCreateTagFromMap(Map<String, Tag> tagMap, String name, String type, Long tenantId) {
        String key = name + "_" + type;
        Tag tag = tagMap.get(key);
        if (tag == null) {
            tag = new Tag();
            tag.setId(snowflake.nextId());
            tag.setName(name);
            tag.setType(type);
            tag.setTenantId(tenantId);
            tag = tagRepository.save(tag);
            tagMap.put(key, tag);
        }
        return tag;
    }
    private static final int BATCH_SIZE = 10;
    private <T> void batchSave(List<T> entities, Consumer<List<T>> saveFunction) {
        for (int i = 0; i < entities.size(); i += BATCH_SIZE) {
            int end = Math.min(i + BATCH_SIZE, entities.size());
            List<T> batch = entities.subList(i, end);
            saveFunction.accept(batch);
        }
    }
}
