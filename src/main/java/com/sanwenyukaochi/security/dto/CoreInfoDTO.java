package com.sanwenyukaochi.security.dto;


import com.sanwenyukaochi.security.entity.Clip;
import lombok.Data;

import java.util.List;

@Data
public class CoreInfoDTO {
    private String start;
    private String end;
    private String topic;
    private String type;
    private List<Clip.SubtitleDTO> sentenceInfo;
}