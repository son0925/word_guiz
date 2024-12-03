package com.example.word.common.domain.board.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardStatus {

    REGISTER("등록"),
    UNREGISTER("삭제"),
    UPDATE("수정"),
    NOTICE("공지사항"),

    ;

    private final String description;
}
