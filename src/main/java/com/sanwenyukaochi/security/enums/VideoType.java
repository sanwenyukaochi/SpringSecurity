package com.sanwenyukaochi.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VideoType {
    SHORT_DRAMA("短剧"),
    LIVE_STREAM("直播");
    private final String type;
}
