package com.example.word.common.domain.word.service;

import com.example.word.common.domain.word.model.WordDto;
import com.example.word.crud.Converter;
import com.example.word.common.domain.word.db.WordEntity;
import org.springframework.stereotype.Component;

@Component
public class WordConverter implements Converter<WordDto, WordEntity> {


    @Override
    public WordDto toDto(WordEntity wordEntity) {
        return WordDto.builder()
                .wordId(wordEntity.getWordId())
                .userId(wordEntity.getUserId())
                .word(wordEntity.getWord())
                .mean(wordEntity.getMean())
                .build()
                ;
    }

    @Override
    public WordEntity toEntity(WordDto wordDto) {
        return WordEntity.builder()
                .wordId(wordDto.getWordId())
                .userId(wordDto.getUserId())
                .word(wordDto.getWord())
                .mean(wordDto.getMean())
                .build()
                ;
    }
}
