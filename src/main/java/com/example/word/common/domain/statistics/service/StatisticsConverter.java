package com.example.word.common.domain.statistics.service;

import com.example.word.common.annotation.Converter;
import com.example.word.common.domain.statistics.model.StatisticsEntity;
import com.example.word.common.domain.statistics.model.StatisticsResponse;

@Converter
public class StatisticsConverter {

    public StatisticsEntity toEntity(StatisticsResponse response) {
        return StatisticsEntity.builder()
                .id(response.getId())
                .userId(response.getUserId())
                .wordId(response.getWordId())
                .status(response.getStatus())
                .correctAnswerCount(response.getCorrectAnswerCount())
                .totalQuizCount(response.getTotalQuizCount())
                .noQuizCount(response.getNoQuizCount())
                .build();
    }

    public StatisticsResponse toResponse(StatisticsEntity entity) {
        return StatisticsResponse.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .wordId(entity.getWordId())
                .status(entity.getStatus())
                .correctAnswerCount(entity.getCorrectAnswerCount())
                .totalQuizCount(entity.getTotalQuizCount())
                .noQuizCount(entity.getNoQuizCount())
                .build();
    }


}
