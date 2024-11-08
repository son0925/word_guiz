package com.example.word.common.domain.statistics.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import lombok.*;

import java.time.LocalDateTime;

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

    private LocalDateTime lastQuizDate;

    private int correctAnswerCount;

    private int noQuizCount;

    private Long totalQuizCount;

}

