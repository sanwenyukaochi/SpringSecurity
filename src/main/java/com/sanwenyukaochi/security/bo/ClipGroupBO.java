package com.sanwenyukaochi.security.bo;

import lombok.Getter;

@Getter
public class ClipGroupBO {
    private Long id;
    private String title;

    public ClipGroupBO(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
