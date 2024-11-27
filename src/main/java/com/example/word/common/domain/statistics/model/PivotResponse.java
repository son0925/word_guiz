package com.example.word.common.domain.statistics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PivotResponse {

    private String word;

    private int correctAnswerCount;

    private Long totalQuizCount;

}
