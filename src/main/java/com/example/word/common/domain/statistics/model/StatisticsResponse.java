package com.example.word.common.domain.statistics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticsResponse {

    private Long id;

    private String userId;

    private Long wordId;

    private LocalDateTime lastQuizDate;

    private int correctAnswerCount;

    private int noQuizCount;

    private Long totalQuizCount;

}
