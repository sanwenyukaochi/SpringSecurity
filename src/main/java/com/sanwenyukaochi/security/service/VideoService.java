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
import com.sanwenyukaochi.security.entity.Task;
import com.sanwenyukaochi.security.entity.Video;
import com.sanwenyukaochi.security.enums.TaskStatus;
import com.sanwenyukaochi.security.enums.TaskType;
import com.sanwenyukaochi.security.exception.APIException;
import com.sanwenyukaochi.security.repository.*;
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
import java.util.stream.Collectors;
import cn.hutool.core.io.FileUtil;
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
        Task dbTask = taskRepository.findById(videoSliceCallbackBO.getTaskId()).orElseThrow(() -> new APIException(HttpStatus.HTTP_NOT_FOUND,"任务不存在"));
        Video dbVideo = videoRepository.findById(videoSliceCallbackBO.getVideoId()).orElseThrow(() -> new APIException(HttpStatus.HTTP_NOT_FOUND, "视频不存在"));
        if (!videoSliceCallbackBO.getClipGroups().isEmpty()) {
            // 清除视频之前的切片组和切片（如果有的话）
            if (dbVideo.getHasClips()) {
                dbVideo.getClipGroups().clear();
            }
            
            // 设置视频已有切片和大纲
            dbVideo.setHasClips(true);
            dbVideo.setHasOutline(true);
            
            // 创建列表用于批量保存ClipGroup和Clip
            List<ClipGroup> clipGroupsToSave = new ArrayList<>();
            List<Clip> clipsToSave = new ArrayList<>();
            
            // 处理每个切片组
            int groupOrder = 0;
            for (ClipGroupDTO clipGroupDTO : videoSliceCallbackBO.getClipGroups()) {
                // 创建新的切片组
                ClipGroup clipGroup = new ClipGroup();
                clipGroup.setId(snowflake.nextId());
                clipGroup.setVideo(dbVideo);
                clipGroup.setSummary(clipGroupDTO.getSummary());
                clipGroup.setStart(clipGroupDTO.getStart());
                clipGroup.setEnd(clipGroupDTO.getEnd());
                clipGroup.setGroupOrder(groupOrder++);
                clipGroup.setTenantId(dbTask.getTenantId());
                
                // 处理每个核心信息（创建切片）
                if (clipGroupDTO.getCoreInfo() != null && !clipGroupDTO.getCoreInfo().isEmpty()) {
                    int clipOrder = 0;
                    for (CoreInfoDTO coreInfoDTO : clipGroupDTO.getCoreInfo()) {
                        Clip clip = new Clip();
                        clip.setId(snowflake.nextId());
                        clip.setStart(coreInfoDTO.getStart());
                        clip.setEnd(coreInfoDTO.getEnd());
                        clip.setOrderInGroup(clipOrder++);
                        clip.setClipGroup(clipGroup);
                        clip.setTenantId(dbTask.getTenantId());
                        // 处理字幕信息
                        if (videoSliceCallbackBO.isAddSubtitle() && coreInfoDTO.getSentenceInfo() != null && !coreInfoDTO.getSentenceInfo().isEmpty()) {
                            List<Clip.SubtitleDTO> subtitles = coreInfoDTO.getSentenceInfo().stream()
                                    .map(subtitleDTO -> {
                                        Clip.SubtitleDTO subtitle = new Clip.SubtitleDTO();
                                        subtitle.setStart(subtitleDTO.getStart());
                                        subtitle.setEnd(subtitleDTO.getEnd());
                                        subtitle.setText(subtitleDTO.getText());
                                        return subtitle;
                                    })
                                    .collect(Collectors.toList());
                            clip.setSubtitles(subtitles);
                        }
                        
                        // 将切片添加到切片组和待保存列表
                        clipGroup.getClips().add(clip);
                        clipsToSave.add(clip);
                    }
                }
                
                // 将切片组添加到视频和待保存列表
                dbVideo.getClipGroups().add(clipGroup);
                clipGroupsToSave.add(clipGroup);
            }
            
            // 更新任务状态为完成
            dbTask.setStatus(TaskStatus.FINISHED);
            
            // 批量保存所有ClipGroup和Clip
            clipGroupRepository.saveAll(clipGroupsToSave);
            clipRepository.saveAll(clipsToSave);
            
            // 保存视频和任务
            videoRepository.save(dbVideo);
            taskRepository.save(dbTask);
            
            log.info("任务Id：{}, 视频Id：{}, 生成视频大纲成功，批量保存了{}个切片组和{}个切片", dbTask.getId(), dbVideo.getId(), clipGroupsToSave.size(), clipsToSave.size());
        } else {
            log.error("任务Id：{}, 生成视频大纲失败, 失败原因: Python返回Null", dbTask.getId());
            dbTask.setStatus(TaskStatus.FAILED);
            taskRepository.save(dbTask);
        }
    }
}
