package com.example.word.common.domain.statistics.service;

import com.example.word.common.annotation.Converter;
import com.example.word.common.domain.statistics.model.StatisticsEntity;
import com.example.word.common.domain.statistics.model.StatisticsId;
import com.example.word.common.domain.statistics.model.StatisticsResponse;
import com.example.word.common.domain.user.service.UserConverter;
import com.example.word.common.domain.word.service.WordConverter;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class StatisticsConverter {

    private final UserConverter userConverter;
    private final WordConverter wordConverter;

    public StatisticsEntity toEntity(StatisticsResponse response) {
        return StatisticsEntity.builder()
                .id(StatisticsId.builder()
                        .wordId(response.getWordId())
                        .userId(response.getUserId())
                        .build())
                .status(response.getStatus())
                .correctAnswerCount(response.getCorrectAnswerCount())
                .totalQuizCount(response.getTotalQuizCount())
                .noQuizCount(response.getNoQuizCount())
                .user(response.getUser())
                .word(response.getWord())
                .build();
    }

    public StatisticsResponse toResponse(StatisticsEntity entity) {
        return StatisticsResponse.builder()
                .userId(entity.getId().getUserId())
                .wordId(entity.getId().getWordId())
                .status(entity.getStatus())
                .correctAnswerCount(entity.getCorrectAnswerCount())
                .totalQuizCount(entity.getTotalQuizCount())
                .noQuizCount(entity.getNoQuizCount())
                .user(entity.getUser())
                .word(entity.getWord())
                .build();
    }


}
