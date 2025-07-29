package com.sanwenyukaochi.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDTO {
    private Long id;
    private String videoName;
    private Long fileSize;
    private Double duration;
    private String videoPath;
    private String coverImage;

    public VideoDTO(Long id, String fullFileNameWithName) {
        this.id = id;
        this.videoName = fullFileNameWithName;
    }
}
