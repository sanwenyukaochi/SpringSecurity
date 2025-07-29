package com.sanwenyukaochi.security.controller;

import com.sanwenyukaochi.security.dto.ClipDTO;
import com.sanwenyukaochi.security.service.ClipService;
import com.sanwenyukaochi.security.vo.ClipVO;
import com.sanwenyukaochi.security.vo.Result;
import com.sanwenyukaochi.security.vo.page.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/clip")
@RequiredArgsConstructor
public class ClipController {
    private final ClipService clipService;
    @GetMapping("/queryClip/{clipGroupId}")
    public Result<PageVO<ClipVO>> getClip(@RequestParam(defaultValue = "0") int currentPage, @RequestParam(defaultValue = "6") int size,
                                          @PathVariable Long clipGroupId) {
        Pageable pageable = PageRequest.of(currentPage, size);
        Page<ClipDTO> clipGroups = clipService.findAllClipsByClipGroupId(clipGroupId, pageable);
        return Result.success(PageVO.from(clipGroups.map(clip -> new ClipVO(
                clip.getClipId(),
                clip.getStart(),
                clip.getEnd(),
                clip.getCoverImage()
        ))));
    }
}
