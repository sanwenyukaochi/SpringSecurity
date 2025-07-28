package com.sanwenyukaochi.security.dto;

import lombok.Data;

@Data
public class ClipDTO {
    
    private Long clipId;
    private String start;
    private String end;

    public ClipDTO(Long id, String start, String end) {
        this.clipId = id;
        this.start = start;
        this.end = end;
    }
}
