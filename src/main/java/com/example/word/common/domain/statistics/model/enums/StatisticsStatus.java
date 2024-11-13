package com.example.word.common.domain.statistics.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatisticsStatus {

    ANSWER("정답"),
    WRONG_ANSWER("오답"),
    NO_ANSWER("하지 않음")
    ;

    private final String status;
}
