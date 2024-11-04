package com.example.word.common.api;


import com.example.word.common.error.ErrorCodeIfs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private Integer resultCode;

    private String resultMessage;

    private String resultDescription;


    public static Result OK() {
        return Result.builder()
                .resultCode(200)
                .resultMessage("OK")
                .resultDescription("성공")
                .build()
                ;
    }

    public static Result ERROR() {
        return Result.builder()
                .resultCode(500)
                .resultMessage("Error")
                .resultDescription("에러")
                .build()
                ;
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs) {
        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode())
                .resultMessage("Error")
                .resultDescription(errorCodeIfs.getDescription())
                .build()
                ;
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs, String description) {
        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode())
                .resultMessage("Error")
                .resultDescription(description)
                .build()
                ;
    }

}
