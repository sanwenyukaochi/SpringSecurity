package com.sanwenyukaochi.security.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClipGroupVO {
    private Long clipGroupId;
    private String summary;
    private String start;
    private String end;
    private Integer clipCount;
}
