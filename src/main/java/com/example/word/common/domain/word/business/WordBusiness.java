package com.example.word.common.domain.word.business;

import com.example.word.common.annotation.Business;
import com.example.word.common.api.Api;
import com.example.word.common.domain.token.business.TokenBusiness;
import com.example.word.common.domain.user.model.User;
import com.example.word.common.domain.word.converter.WordConverter;
import com.example.word.common.domain.word.model.WordRequest;
import com.example.word.common.domain.word.model.WordResponse;
import com.example.word.common.domain.word.service.WordService;
import com.example.word.common.error.ErrorCode;
import com.example.word.common.error.WordErrorCode;
import com.example.word.common.exception.ApiException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Business
@RequiredArgsConstructor
public class WordBusiness {

    private final WordService wordService;
    private final WordConverter wordConverter;

    public WordResponse saveWord(Api<WordRequest> wordRequest, User user) {

        var wordData = wordRequest.getBody();

        if (Objects.isNull(wordData) || wordData.getWord() == null || wordData.getMean() == null) {
            throw new ApiException(ErrorCode.SERVER_ERROR);
        }

        var word = wordData.getWord();
        var mean = wordData.getMean();

        var userId = user.getUserId();

        var wordEntity = wordService.wordSave(word, mean, userId);

        return wordConverter.toResponse(wordEntity);
    }

    public WordResponse updateWord(Api<WordRequest> wordRequest, User user) {
        var wordData = wordRequest.getBody();

        if (Objects.isNull(wordData) || wordData.getWord() == null || wordData.getMean() == null) {
            throw new ApiException(ErrorCode.SERVER_ERROR);
        }

        return null;
    }

    public List<WordResponse> getWordList() {
        return null;
    }

    public void deleteWord() {

    }

}
