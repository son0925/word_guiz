package com.example.word.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 단어 2000~2999
 */

@Getter
@AllArgsConstructor
public enum WordErrorCode implements ErrorCodeIfs{
    
    WORD_NOT_FOUND(404, 2404, "단어를 찾을 수 없음"),
    BAD_REQUEST_WORD(400, 2413, "잘못된 단어 요청"),
    EXISTS_WORD(400, 2405, "이미 존재하는 단어"),
    
    ;
    
    
    private final Integer httpStatusCode;
    
    private final Integer errorCode;
    
    private final String description;
}
