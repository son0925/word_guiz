package com.example.word.common.domain.word.service;

import com.example.word.common.annotation.Converter;
import com.example.word.common.domain.word.model.WordEntity;
import com.example.word.common.domain.word.model.WordResponse;

import java.time.LocalDateTime;

@Converter
public class WordConverter {

    public WordEntity toEntity(WordResponse wordResponse) {
        return WordEntity.builder()
                .wordId(wordResponse.getWordId())
                .word(wordResponse.getWord())
                .mean(wordResponse.getMean())
                .addedDate(wordResponse.getAddedDate())
                .build()
                ;
    }


    public WordResponse toResponse(WordEntity wordEntity) {
        return WordResponse.builder()
                .wordId(wordEntity.getWordId())
                .word(wordEntity.getWord())
                .mean(wordEntity.getMean())
                .addedDate(wordEntity.getAddedDate())
                .build()
                ;
    }
}
