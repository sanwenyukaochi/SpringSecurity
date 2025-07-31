package com.sanwenyukaochi.security.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TagVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    private String type;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdAt;
    private int clipCount;
}
