package com.sanwenyukaochi.security.service;

import com.sanwenyukaochi.security.constant.FileConstants;
import com.sanwenyukaochi.security.entity.Clip;
import com.sanwenyukaochi.security.entity.ClipGroup;
import com.sanwenyukaochi.security.entity.Video;
import com.sanwenyukaochi.security.storage.FileStorage;
import com.sanwenyukaochi.security.utils.FfmpegUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoCoverService {
    private final FileStorage fileStorage;
    @Value("${storage.sfs.local-dir}")
    private String localDir;
    
    @SneakyThrows
    public String generateVideoCover(Video video, Long tenantId, Long createdBy) {
        String coverObjectPath = String.format("%s/%s/%s/%s/%s.%s", tenantId, createdBy, video.getId(), FileConstants.COVER_DIR_NAME, video.getId(), FileConstants.EXT_JPG);
        String coverUrl = String.format("%s/%s", fileStorage.getBucketPath(), coverObjectPath);
        try {
            String videoPath = getLocalVideoPath(video);
            Path localCoverPath = Paths.get(localDir, coverObjectPath);
            if (localCoverPath.getParent() != null && Files.notExists(localCoverPath.getParent())) Files.createDirectories(localCoverPath.getParent());
            FfmpegUtils.getFirstImageFromVideo(videoPath, "00:00:00.000", localCoverPath.toString());
            fileStorage.uploadFileByFileStream(coverObjectPath, localCoverPath.toString());
            Files.deleteIfExists(localCoverPath);
            log.info("视频封面生成成功: videoId={}, coverPath={}", video.getId(), coverUrl);
            return coverUrl;
        } catch (Exception e) {
            log.error("视频封面生成失败: videoId={}, error={}", video.getId(), e.getMessage(), e);
            return null;
        }
    }

    @SneakyThrows
    public String generateClipGroupCover(Video video, ClipGroup group, Long tenantId, Long createdBy) {
        String coverObjectPath = String.format("%s/%s/%d/covers/clipGroup/%s.%s", tenantId, createdBy, video.getId(), group.getId(), FileConstants.EXT_JPG);
        String coverUrl = String.format("%s/%s", fileStorage.getBucketPath(), coverObjectPath);
        try {
            String videoPath = getLocalVideoPath(video);
            Path localCoverPath = Paths.get(localDir, coverObjectPath);
            if (localCoverPath.getParent() != null && Files.notExists(localCoverPath.getParent())) Files.createDirectories(localCoverPath.getParent());
            FfmpegUtils.getFirstImageFromVideo(videoPath, group.getStart(), localCoverPath.toString());
            fileStorage.uploadFileByFileStream(coverObjectPath, localCoverPath.toString());
            Files.deleteIfExists(localCoverPath);
            log.info("ClipGroup封面生成成功: groupId={}, coverPath={}", group.getId(), coverUrl);
            return coverUrl;
        } catch (Exception e) {
            log.error("ClipGroup封面生成失败: groupId={}, error={}", group.getId(), e.getMessage(), e);
            return null;
        }
    }

    @SneakyThrows
    public String generateClipCover(Video video, Clip clip, Long tenantId, Long createdBy) {
        String coverObjectPath = String.format("%s/%s/%d/covers/clips/%s.%s", tenantId, createdBy, video.getId(), clip.getId(), FileConstants.EXT_JPG);
        String coverUrl = String.format("%s/%s", fileStorage.getBucketPath(), coverObjectPath);
        try {
            String videoPath = getLocalVideoPath(video);

            Path localCoverPath = Paths.get(localDir, coverObjectPath);
            if (localCoverPath.getParent() != null && Files.notExists(localCoverPath.getParent())) Files.createDirectories(localCoverPath.getParent());
            FfmpegUtils.getFirstImageFromVideo(videoPath, clip.getStart(), localCoverPath.toString());
            fileStorage.uploadFileByFileStream(coverObjectPath, localCoverPath.toString());
            Files.deleteIfExists(localCoverPath);
            log.info("Clip封面生成成功: clipId={}, coverPath={}", clip.getId(), coverUrl);
            return coverUrl;
        } catch (Exception e) {
            log.error("Clip封面生成失败: clipId={}, error={}", clip.getId(), e.getMessage(), e);
            return null;
        }
    }

    @SneakyThrows
    private String getLocalVideoPath(Video video) {
        String videoObjectPath = video.getVideoPath().replace(fileStorage.getBucketPath() + "/", "");
        Path localVideoPath = Paths.get(localDir, videoObjectPath);
        if (Files.notExists(localVideoPath)) {
            if (localVideoPath.getParent() != null && Files.notExists(localVideoPath.getParent())) {
                Files.createDirectories(localVideoPath.getParent());
            }
            fileStorage.downloadFileByCheckpoint(videoObjectPath, localVideoPath.toString());
        }
        return localVideoPath.toString();
    }
}