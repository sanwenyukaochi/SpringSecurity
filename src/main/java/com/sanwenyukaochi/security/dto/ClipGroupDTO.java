package com.sanwenyukaochi.security.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClipGroupDTO {
    private String start;
    private String end;
    private String summary;
    private List<CoreInfoDTO> coreInfo;
}
