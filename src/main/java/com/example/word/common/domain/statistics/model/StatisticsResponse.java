package com.example.word.common.domain.statistics.model;

import com.example.word.common.domain.statistics.model.enums.StatisticsStatus;
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

    private StatisticsStatus status;

    private int correctAnswerCount;

    private int noQuizCount;

    private Long totalQuizCount;

}
