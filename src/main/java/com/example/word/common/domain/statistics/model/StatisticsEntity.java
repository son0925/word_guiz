package com.example.word.common.domain.statistics.model;

import com.example.word.common.domain.statistics.model.enums.StatisticsStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import lombok.*;

@Entity
@Table(name = "statistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private Long wordId;

    @Enumerated(EnumType.STRING)
    private StatisticsStatus status;

    private int correctAnswerCount;

    private int noQuizCount;

    private Long totalQuizCount;

}

