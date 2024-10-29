package com.example.word.statistics.db;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "statistics")
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StatisticsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private Long wordId;

    private Integer quizYn;

    private LocalDateTime lastQuizDate;

    private Integer correctAnswerCount;

    private Integer cnt;

}
