package com.sanwenyukaochi.security.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClipGroupDTO {
    private Long id;
    private String start;
    private String end;
    private String summary;
    private List<CoreInfoDTO> coreInfo;
//    private List<Clip> clips;
    private Integer clipCount;

    public ClipGroupDTO(Long id, String summary, String start, String end, int clipCount) {
        this.id = id;
        this.summary = summary;
        this.start = start;
        this.end = end;
        this.clipCount = clipCount;
    }
}
