package com.sanwenyukaochi.security.controller;


import com.sanwenyukaochi.security.enums.VideoAspectRatio;
import com.sanwenyukaochi.security.vo.Result;
import com.sanwenyukaochi.security.vo.VideoAspectRatioVO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/videoMerge")
@RequiredArgsConstructor
public class VideoMergeController {

    @GetMapping("/aspectRatios")
    @Operation(summary = "视频合成比例类型")
    public Result<List<VideoAspectRatioVO>> getDetailedAspectRatios() {
        return Result.success(Arrays.stream(VideoAspectRatio.values()).map(ar -> new VideoAspectRatioVO(
                ar.name(),
                ar.getRatio()
        )).collect(Collectors.toList()));
    }
}
