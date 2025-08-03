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
    private String name;
    private String code;
    private String path;
    private String type;
    private Integer sort;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<RouterVO> children;
    public boolean isMenu() {
        return "1".equals(type);
    }
    public boolean isButton() {
        return "2".equals(type);
    }
}