package com.sanwenyukaochi.security.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sanwenyukaochi.security.ao.SliceVideoTmpCallbackAO;
import com.sanwenyukaochi.security.bo.VideoSliceCallbackBO;
import com.sanwenyukaochi.security.service.VideoService;
import com.sanwenyukaochi.security.vo.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class VideoCallBackTmpController {
    private final VideoService videoService;

    @PostMapping("/clipVideoCallBack")
    public Result<String> handleClipVideoCallback(@RequestBody SliceVideoTmpCallbackAO sliceVideoCallbackAO) throws JsonProcessingException {
        // TODO 清理 SliceVideoTmpCallbackAO，这里通知python修改格式
        VideoSliceCallbackBO videoSliceCallbackBO = new VideoSliceCallbackBO(
                Long.parseLong(sliceVideoCallbackAO.getTaskId()),
                Long.parseLong(sliceVideoCallbackAO.getVideoId()),
                sliceVideoCallbackAO.getVideoPath(),
                sliceVideoCallbackAO.getVideoType(),
                sliceVideoCallbackAO.getClipSections(),
                sliceVideoCallbackAO.isAddSubtitle()
        );
        videoService.handleClipVideoCallbackAsync(videoSliceCallbackBO);
        return Result.success("收到回调");
    }

}
