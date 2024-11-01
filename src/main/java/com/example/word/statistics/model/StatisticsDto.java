package com.example.word.statistics.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StatisticsDto {

    private Long id;

    private String userId;

    private Long wordId;

    private Integer quizYn;

    private LocalDateTime lastQuizDate;

    private Integer correctAnswerCount;

    private Integer cnt;

}
