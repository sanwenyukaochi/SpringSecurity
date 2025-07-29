package com.sanwenyukaochi.security.dto;

import lombok.Data;

@Data
public class ClipDTO {
    
    private Long clipId;
    private String start;
    private String end;
    private String coverImage;

    public ClipDTO(Long id, String start, String end, String coverImage) {
        this.clipId = id;
        this.start = start;
        this.end = end;
        this.coverImage = coverImage;
    }
}
