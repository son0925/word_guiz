package com.example.word.common.domain.word.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WordUpdateRequest {

    @NotNull(message = "wordId is required")
    private Long wordId;

    @NotBlank(message = "word is required")
    private String word;

    @NotBlank(message = "mean is required")
    private String mean;

    private String memo;
}

