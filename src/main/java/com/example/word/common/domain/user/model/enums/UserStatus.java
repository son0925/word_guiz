package com.example.word.common.domain.user.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    REGISTER("등록"),
    UNREGISTER("해지"),

    ;

    private final String status;
}
