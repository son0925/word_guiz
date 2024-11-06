package com.example.word.common.domain.word.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WordUpdateRequest {

    @NotBlank
    private Long wordId;

    @NotBlank
    private String word;

    @NotBlank
    private String mean;

}
