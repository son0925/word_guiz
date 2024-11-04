package com.example.word.common.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ErrorCodeIfs{

    OK(200, 200, "OK"),
    BAD_REQUEST(400, 400, "BAD_REQUEST"),
    NULL_POINT(500, 512, "NULL Point"),
    SERVER_ERROR(500, 500, "SERVER Error")
    ;

    private final Integer httpStatusCode;

    private final Integer errorCode;

    private final String description;

}
