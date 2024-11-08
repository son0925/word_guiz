package com.example.word.common.domain.word.business;

import com.example.word.common.annotation.Business;
import com.example.word.common.api.Api;
import com.example.word.common.api.Pagination;
import com.example.word.common.domain.statistics.business.StatisticsBusiness;
import com.example.word.common.domain.user.model.User;
import com.example.word.common.domain.word.converter.WordConverter;
import com.example.word.common.domain.word.model.*;
import com.example.word.common.domain.word.service.WordService;
import com.example.word.common.error.ErrorCode;
import com.example.word.common.error.UserErrorCode;
import com.example.word.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Business
@RequiredArgsConstructor
public class WordBusiness {

    private final WordService wordService;
    private final WordConverter wordConverter;

    private final StatisticsBusiness statisticsBusiness;

    @Transactional
    public WordResponse saveWord(Api<WordSaveRequest> wordRequest, User user) {

        var wordData = wordRequest.getBody();

        if (Objects.isNull(wordData) || wordData.getWord() == null || wordData.getMean() == null) {
            throw new ApiException(ErrorCode.SERVER_ERROR);
        }

        var word = wordData.getWord();
        var mean = wordData.getMean();

        var userId = user.getUserId();

        var wordEntity = wordService.wordSave(word, mean, userId);

        // todo Statistics 생성
        statisticsBusiness.create(wordEntity.getWordId(), userId);

        return wordConverter.toResponse(wordEntity);
    }

    @Transactional
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

        // todo Statistics update

        return wordConverter.toResponse(wordEntity);
    }

    public Api<List<WordResponse>> getWordList(User user, Pageable pageable) {

        if (Objects.isNull(user)) {
            throw new ApiException(ErrorCode.SERVER_ERROR);
        }

        var userId = user.getUserId();

        // WordEntity의 페이지 정보를 포함한 목록 조회
        var wordPage = wordService.getWordList(userId, pageable);

        // WordEntity 리스트를 WordResponse 리스트로 변환
        var wordResponses = wordPage.getContent().stream()
                .map(wordConverter::toResponse)
                .toList();

        // Pagination 객체 생성
        var pagination = Pagination.builder()
                .curPage(wordPage.getNumber())
                .curElement(wordPage.getNumberOfElements())
                .size(wordPage.getSize())
                .totalPage(wordPage.getTotalPages())
                .totalElement(wordPage.getTotalElements())
                .build();

        return Api.OK(wordResponses, pagination);
    }

    @Transactional
    public void deleteWord(Api<WordDeleteRequest> wordIdApi, User user) {

        if (Objects.isNull(user) || Objects.isNull(wordIdApi)) {
            throw new ApiException(UserErrorCode.DO_NOT_LOGIN);
        }

        // todo Statistics 삭제하기

        var wordId = wordIdApi.getBody().getWordId();
        var userId = user.getUserId();

        wordService.deleteWord(wordId, userId);
    }



}
