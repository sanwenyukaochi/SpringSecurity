package com.sanwenyukaochi.security.service;

import com.sanwenyukaochi.security.bo.TagBO;
import com.sanwenyukaochi.security.dto.TagDTO;
import com.sanwenyukaochi.security.entity.Tag;
import com.sanwenyukaochi.security.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    public Page<TagDTO> findAllTag(TagBO tagBO, Pageable pageable) {
        Page<Tag> all = tagRepository.findAll(pageable);
        return all.map(tag -> new TagDTO(
                tag.getId(),
                tag.getName(),
                tag.getType(),
                tag.getCreatedAt(),
                tagRepository.countByClipTags_TagId(tag.getId())
        ));
    }
}
