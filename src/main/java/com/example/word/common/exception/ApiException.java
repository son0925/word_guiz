package com.example.word.common.exception;

import com.example.word.common.error.ErrorCodeIfs;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException implements ApiExceptionIfs{

    private final ErrorCodeIfs errorCodeIfs;

    private final String errorDescription;

    public ApiException(ErrorCodeIfs errorCodeIfs) {
        super(errorCodeIfs.getDescription());
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = getErrorDescription();
    }

    public ApiException(ErrorCodeIfs errorCodeIfs, String description) {
        super(description);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = description;
    }
}
