package com.sanwenyukaochi.security.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class RouterVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String path;
    private String name;
    private String code;
    private Integer sort;
    private RouterMeta meta;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<RouterVO> children;
    
    @Getter
    @AllArgsConstructor
    public static class RouterMeta {
        private String title;
    }
}