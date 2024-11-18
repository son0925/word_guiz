package com.example.word.common.domain.fetchify.naver.business;

import com.example.word.common.annotation.Business;
import com.example.word.common.domain.fetchify.naver.service.NaverOpenApiService;
import com.example.word.common.domain.word.model.DictionaryResponse;
import com.example.word.common.domain.word.model.NaverApiResponse;
import com.example.word.common.domain.word.service.WordConverter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Business
@RequiredArgsConstructor
public class NaverOpenApiBusiness {

    private final NaverOpenApiService naverOpenApiService;

    private final WordConverter wordConverter;


    public NaverApiResponse searchDictionary(String word) {

        return naverOpenApiService.searchDictionary(word);

    }

}
