package com.example.word.common.domain.word.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WordSaveRequest {

    @NotBlank
    private String word;

    @NotBlank
    private String mean;

    private String memo;

}
