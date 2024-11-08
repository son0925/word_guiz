package com.example.word.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatisticsErrorCode implements ErrorCodeIfs{
    /**
     * 4000~4999
     */

    SAVE_FAILED(400, 4400, "이미 존재하는 단어입니다."),

    ;


    private final Integer httpStatusCode;

    private final Integer errorCode;

    private final String description;

}
