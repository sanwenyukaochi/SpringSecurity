package com.sanwenyukaochi.security.controller;

import com.sanwenyukaochi.security.ao.UpdateClipGroupAO;
import com.sanwenyukaochi.security.bo.ClipGroupBO;
import com.sanwenyukaochi.security.dto.ClipGroupDTO;
import com.sanwenyukaochi.security.service.ClipGroupService;
import com.sanwenyukaochi.security.vo.ClipGroupVO;
import com.sanwenyukaochi.security.vo.Result;
import com.sanwenyukaochi.security.vo.UpdateClipGroupVO;
import com.sanwenyukaochi.security.vo.page.PageVO;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "根据videoId查询切片组")
    public Result<PageVO<ClipGroupVO>> getClipGroup(@RequestParam(defaultValue = "0") int currentPage, @RequestParam(defaultValue = "6") int size, 
                                                    @PathVariable Long videoId) {
        Pageable pageable = PageRequest.of(currentPage, size);
        Page<ClipGroupDTO> clipGroups = clipGroupService.findAllClipGroupsByVideoId(videoId, pageable);
        return Result.success(PageVO.from(clipGroups.map(clipGroup -> new ClipGroupVO(
                clipGroup.getId(),
                clipGroup.getSummary(),
                clipGroup.getStart(),
                clipGroup.getEnd(),
                clipGroup.getCoverImage(),
                clipGroup.getClipCount()
        ))));
    }

    @PutMapping("/rename")
    @Operation(summary = "重命名切片组标题")
    public Result<UpdateClipGroupVO> updateClipGroup(@RequestBody UpdateClipGroupAO updateClipGroupAO) {
        ClipGroupBO newClipGroupBO = new ClipGroupBO(
                updateClipGroupAO.getId(),
                updateClipGroupAO.getTitle()
        );
        ClipGroupDTO clipGroupDTO = clipGroupService.updateClipGroup(newClipGroupBO);
        return Result.success(new UpdateClipGroupVO(
                clipGroupDTO.getId(),
                clipGroupDTO.getSummary()
        ));
    }
}
