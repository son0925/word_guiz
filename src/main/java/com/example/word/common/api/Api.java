package com.example.word.common.api;

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

    private T body;


    public static <T>Api<T> OK(T data) {
        return Api.<T>builder()
                .result(Result.OK())
                .body(data)
                .build();
    }
}
