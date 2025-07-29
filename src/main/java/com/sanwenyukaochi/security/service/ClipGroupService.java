package com.sanwenyukaochi.security.service;

import cn.hutool.http.HttpStatus;
import com.sanwenyukaochi.security.bo.ClipGroupBO;
import com.sanwenyukaochi.security.dto.ClipGroupDTO;
import com.sanwenyukaochi.security.entity.ClipGroup;
import com.sanwenyukaochi.security.exception.APIException;
import com.sanwenyukaochi.security.repository.ClipGroupRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClipGroupService {
    private final ClipGroupRepository clipGroupRepository;

    public Page<ClipGroupDTO> findAllClipGroupsByVideoId(Long videoId, Pageable pageable) {
        Page<ClipGroup> clipGroups = clipGroupRepository.findAllByVideoId(videoId, pageable);
        return clipGroups.map(clipGroup -> new ClipGroupDTO(
                clipGroup.getId(),
                clipGroup.getSummary(),
                clipGroup.getStart(),
                clipGroup.getEnd(),
                clipGroup.getCoverImage(),
                clipGroup.getClips().size()
        ));
    }

    @Transactional
    public ClipGroupDTO updateClipGroup(ClipGroupBO clipGroupBO) {
        ClipGroup dbClipGroup = clipGroupRepository.findById(clipGroupBO.getId()).orElseThrow(() -> new APIException(HttpStatus.HTTP_NOT_FOUND, "切片组不存在"));
        dbClipGroup.setSummary(clipGroupBO.getTitle());
        clipGroupRepository.save(dbClipGroup);
        return new ClipGroupDTO(
                dbClipGroup.getId(),
                dbClipGroup.getSummary()
        );
    }
}
