package com.sanwenyukaochi.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VideoAspectRatio {
    RATIO_9_16("9:16"),
    RATIO_16_9("16:9"),
    RATIO_3_4("3:4"),
    RATIO_4_3("4:3"),
    RATIO_1_1("1:1");
    private final String ratio;
}
