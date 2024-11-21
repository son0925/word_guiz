package com.example.word.exceptionhandler;

import com.example.word.common.api.Api;
import com.example.word.common.error.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = 0)
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Api<Object> handlerValidationExceptions(MethodArgumentNotValidException exception) {
        var errorList = exception.getFieldErrors().stream()
                .map(it -> {
                    var format = "%s";
                    return String.format(format, it.getDefaultMessage());
                })
                .toList()
                ;

        return Api.ERROR(ErrorCode.BAD_REQUEST, errorList.toString());
    }

}
