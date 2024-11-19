package com.example.word.common.domain.word.service;

import com.example.word.common.annotation.Converter;
import com.example.word.common.domain.word.model.DictionaryResponse;
import com.example.word.common.domain.word.model.NaverApiResponse;
import com.example.word.common.domain.word.model.WordEntity;
import com.example.word.common.domain.word.model.WordResponse;

import java.util.ArrayList;
import java.util.List;

@Converter
public class WordConverter {

    public WordResponse toResponse(WordEntity wordEntity) {
        return WordResponse.builder()
                .wordId(wordEntity.getWordId())
                .word(wordEntity.getWord())
                .mean(wordEntity.getMean())
                .addedDate(wordEntity.getAddedDate())
                .memo(wordEntity.getMemo())
                .build()
                ;
    }

    public List<DictionaryResponse> convertToDictionaryResponse(NaverApiResponse response) {
        List<DictionaryResponse> results = new ArrayList<>();
        if (response != null && response.getItems() != null) {
            for (NaverApiResponse.Item item : response.getItems()) {
                DictionaryResponse dictionaryResponse = new DictionaryResponse(
                        item.getTitle(),
                        item.getLink(),
                        item.getDescription()
                );
                results.add(dictionaryResponse);
            }
        }
        return results;
    }
}
