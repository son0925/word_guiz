package com.example.word.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * User Error 1000~1999
 */

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCodeIfs{

    USER_NOT_FOUND(400, 1404, "유저를 찾을 수 없습니다"),
    DO_NOT_LOGIN(401, 1414, "로그인을 해주세요")

    ;


    private final Integer httpStatusCode;

    private final Integer errorCode;

    private final String description;
}
