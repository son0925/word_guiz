package com.example.word.common.domain.streaks.service;

import com.example.word.common.annotation.Converter;
import com.example.word.common.domain.streaks.model.StreaksEntity;
import com.example.word.common.domain.streaks.model.StreaksResponse;

@Converter
public class StreaksConverter {

    public StreaksResponse toResponse(StreaksEntity entity) {
        return StreaksResponse.builder()
                .userId(entity.getUserId())
                .date(entity.getDate())
                .problemsSolved(entity.getProblemsSolved())
                .build();
    }

}
