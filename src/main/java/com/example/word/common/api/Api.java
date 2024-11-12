package com.example.word.common.api;

import com.example.word.common.error.ErrorCodeIfs;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Api<T>{

    private Result result;

    @Valid
    private T body;

    private Pagination pagination;


    public static <T>Api<T> OK(T data) {
        return Api.<T>builder()
                .result(Result.OK())
                .body(data)
                .build();
    }

    public static <T>Api<T> OK(T data, Pagination pagination) {
        return Api.<T>builder()
                .result(Result.OK())
                .body(data)
                .pagination(pagination)
                .build();
    }

    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs) {
        return Api.builder()
                .result(Result.ERROR(errorCodeIfs))
                .body(null)
                .build()
                ;
    }

    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs, String description) {
        return Api.builder()
                .result(Result.ERROR(errorCodeIfs, description))
                .body(description)
                .build()
                ;
    }
}
