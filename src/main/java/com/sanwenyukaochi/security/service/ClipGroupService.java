package com.sanwenyukaochi.security.service;

import com.sanwenyukaochi.security.dto.ClipGroupDTO;
import com.sanwenyukaochi.security.entity.ClipGroup;
import com.sanwenyukaochi.security.repository.ClipGroupRepository;
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
                clipGroup.getClips().size()
        ));
    }
}
