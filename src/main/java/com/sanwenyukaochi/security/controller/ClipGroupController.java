package com.sanwenyukaochi.security.controller;

import com.sanwenyukaochi.security.dto.ClipGroupDTO;
import com.sanwenyukaochi.security.service.ClipGroupService;
import com.sanwenyukaochi.security.vo.ClipGroupVO;
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
@RequestMapping("/api/clipGroup")
@RequiredArgsConstructor
public class ClipGroupController {
    private final ClipGroupService clipGroupService;
    @GetMapping("/queryClipGroup/{videoId}")
    public Result<PageVO<ClipGroupVO>> getClipGroup(@RequestParam(defaultValue = "0") int currentPage, @RequestParam(defaultValue = "6") int size, 
                                                    @PathVariable Long videoId) {
        Pageable pageable = PageRequest.of(currentPage, size);
        Page<ClipGroupDTO> clipGroups = clipGroupService.findAllClipGroupsByVideoId(videoId, pageable);
        return Result.success(PageVO.from(clipGroups.map(clipGroup -> new ClipGroupVO(
                clipGroup.getId(),
                clipGroup.getSummary(),
                clipGroup.getStart(),
                clipGroup.getEnd(),
                clipGroup.getClipCount()
        ))));
    }
}
