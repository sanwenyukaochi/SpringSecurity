package com.sanwenyukaochi.security.controller;

import com.sanwenyukaochi.security.ao.SliceVideoTmpCallbackAO;
import com.sanwenyukaochi.security.bo.VideoSliceCallbackBO;
import com.sanwenyukaochi.security.service.VideoService;
import com.sanwenyukaochi.security.vo.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class VideoCallBackTmpController {
    private final VideoService videoService;
    private final RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/clipVideoCallBack")
    public Result<String> handleClipVideoCallback(@RequestBody SliceVideoTmpCallbackAO sliceVideoCallbackAO) {
        // TODO 清理 SliceVideoTmpCallbackAO，这里通知python修改格式
        VideoSliceCallbackBO videoSliceCallbackBO = new VideoSliceCallbackBO(
                Long.parseLong(sliceVideoCallbackAO.getTaskId()),
                Long.parseLong(sliceVideoCallbackAO.getVideoId()),
                sliceVideoCallbackAO.getVideoPath(),
                sliceVideoCallbackAO.getVideoType(),
                sliceVideoCallbackAO.getClipSections().stream()
                        .map(section -> new VideoSliceCallbackBO.VideoClipGroupBO(
                                section.getStart(),
                                section.getEnd(),
                                section.getSummary(),
                                section.getCoreInfo().stream()
                                        .map(core -> new VideoSliceCallbackBO.VideoClipBO(
                                                core.getStart(),
                                                core.getEnd(),
                                                core.getTopic(),
                                                core.getType(),
                                                core.getSentenceInfo()
                                        ))
                                        .collect(Collectors.toList())
                        ))
                        .collect(Collectors.toList()),
                sliceVideoCallbackAO.isAddSubtitle()
        );
        redisTemplate.opsForValue().set(sliceVideoCallbackAO.getTaskId(), sliceVideoCallbackAO, 30, TimeUnit.DAYS);
        videoService.handleClipVideoCallbackAsync(videoSliceCallbackBO);
        return Result.success("收到回调");
    }

}
