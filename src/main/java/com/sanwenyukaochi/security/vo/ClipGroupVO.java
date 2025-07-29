package com.sanwenyukaochi.security.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClipGroupVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long clipGroupId;
    private String summary;
    private String start;
    private String end;
    private String coverImage;
    private Integer clipCount;
}
