package com.example.word.common.domain.word.business;

import com.example.word.common.annotation.Business;
import com.example.word.common.api.Api;
import com.example.word.common.domain.user.model.User;
import com.example.word.common.domain.word.converter.WordConverter;
import com.example.word.common.domain.word.model.WordDeleteRequest;
import com.example.word.common.domain.word.model.WordSaveRequest;
import com.example.word.common.domain.word.model.WordResponse;
import com.example.word.common.domain.word.model.WordUpdateRequest;
import com.example.word.common.domain.word.service.WordService;
import com.example.word.common.error.ErrorCode;
import com.example.word.common.error.TokenErrorCode;
import com.example.word.common.error.UserErrorCode;
import com.example.word.common.exception.ApiException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Business
@RequiredArgsConstructor
public class WordBusiness {

    private final WordService wordService;
    private final WordConverter wordConverter;

    public WordResponse saveWord(Api<WordSaveRequest> wordRequest, User user) {

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

    public WordResponse updateWord(Api<WordUpdateRequest> wordRequest, User user) {
        var wordData = wordRequest.getBody();

        System.out.println("hi");
        if (Objects.isNull(wordData) || wordData.getWord() == null || wordData.getMean() == null || Objects.isNull(user)) {
            throw new ApiException(ErrorCode.NULL_POINT);
        }

        var wordId = wordData.getWordId();
        var word = wordData.getWord();
        var mean = wordData.getMean();
        var userId = user.getUserId();

        var wordEntity = wordService.wordUpdate(wordId, word, mean, userId);

        return wordConverter.toResponse(wordEntity);
    }

    public List<WordResponse> getWordList(User user) {

        if (Objects.isNull(user)) {
            throw new ApiException(ErrorCode.SERVER_ERROR);
        }

        var userId = user.getUserId();

        return wordService.getWordList(userId).stream()
                .map(wordConverter::toResponse)
                .toList();
    }

    @Transactional
    public void deleteWord(Api<WordDeleteRequest> wordIdApi, User user) {

        if (Objects.isNull(user) || Objects.isNull(wordIdApi)) {
            throw new ApiException(UserErrorCode.DO_NOT_LOGIN);
        }

        var wordId = wordIdApi.getBody().getWordId();
        var userId = user.getUserId();

        wordService.deleteWord(wordId, userId);
    }

}
