package com.example.word.common.domain.user.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

    ADMIN("관리자"),
    USER("사용자"),
    VIP("VIP"),

    ;

    private final String description;
}
