package com.sanwenyukaochi.security.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClipDTO {
    private Long id;
    private String start;
    private String end;
    private String coverImage;
    private String videoPath;
    private List<TagDTO> tags;
    public ClipDTO(Long id, String start, String end, String coverImage, String videoPath, List<TagDTO> tags) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.coverImage = coverImage;
        this.videoPath = videoPath;
        this.tags = tags;
    }
}
