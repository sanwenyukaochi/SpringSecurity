package com.sanwenyukaochi.security.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TagVO {
    private Long id;
    private String name;
    private String type;
    private Long createdAt;
}
