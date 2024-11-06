package com.example.word.common.domain.word.converter;

import com.example.word.common.annotation.Converter;
import com.example.word.common.domain.word.model.WordEntity;
import com.example.word.common.domain.word.model.WordResponse;

@Converter
public class WordConverter {

    public WordEntity toEntity(WordResponse wordResponse) {
        return WordEntity.builder()
                .wordId(wordResponse.getWordId())
                .word(wordResponse.getWord())
                .mean(wordResponse.getMean())
                .userId(wordResponse.getUserId())
                .build()
                ;
    }


    public WordResponse toResponse(WordEntity wordEntity) {
        return WordResponse.builder()
                .wordId(wordEntity.getWordId())
                .word(wordEntity.getWord())
                .mean(wordEntity.getMean())
                .userId(wordEntity.getUserId())
                .build()
                ;
    }
}
