package com.example.word.common.domain.comment.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReplyStatus {

    REGISTER("등록"),
    UNREGISTER("삭제"),
    UPDATE("수정"),

    ;

    private final String description;
}
