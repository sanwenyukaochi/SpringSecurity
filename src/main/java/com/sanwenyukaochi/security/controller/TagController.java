package com.sanwenyukaochi.security.controller;

import com.sanwenyukaochi.security.ao.QueryTagAO;
import com.sanwenyukaochi.security.bo.TagBO;
import com.sanwenyukaochi.security.dto.TagDTO;
import com.sanwenyukaochi.security.service.TagService;
import com.sanwenyukaochi.security.vo.Result;
import com.sanwenyukaochi.security.vo.TagVO;
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
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    @GetMapping("/queryTag")
    @Operation(summary = "查询标签")
    public Result<PageVO<TagVO>> getTag(@RequestParam(defaultValue = "0") int currentPage, @RequestParam(defaultValue = "6") int size,
                                        @RequestBody QueryTagAO queryTagAO) {
        Pageable pageable = PageRequest.of(currentPage, size);
        TagBO tagBO = new TagBO();
        Page<TagDTO> tagPage = tagService.findAllTag(tagBO, pageable);
        return Result.success(PageVO.from(tagPage.map(tag -> new TagVO(
                tag.getId(),
                tag.getName(),
                tag.getType(),
                tag.getCreatedAt()
        ))));
    }
}
