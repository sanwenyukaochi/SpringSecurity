package com.sanwenyukaochi.security.service;

import com.sanwenyukaochi.security.dto.ClipDTO;
import com.sanwenyukaochi.security.dto.TagDTO;
import com.sanwenyukaochi.security.entity.Clip;
import com.sanwenyukaochi.security.repository.ClipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClipService {
    private final ClipRepository clipRepository;
    public Page<ClipDTO> findAllClipsByClipGroupId(Long clipGroupId, Pageable pageable) {
        Page<Clip> clips = clipRepository.findAllByClipGroupId(clipGroupId, pageable);
        return clips.map(clip -> new ClipDTO(
                clip.getId(),
                clip.getStart(),
                clip.getEnd(),
                clip.getCoverImage(),
                clip.getClipTags().stream()
                        .map(clipTag -> new TagDTO(
                                clipTag.getId(),
                                clipTag.getTag().getName(), 
                                clipTag.getTag().getType()
                        )).collect(Collectors.toList())
        ));
    }
}
