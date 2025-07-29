package com.sanwenyukaochi.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {
    private Long id;
    private String name;
    private String type;
    private Long createdAt;
    private int clipCount;

    public TagDTO(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}