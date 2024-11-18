package com.example.word.common.domain.word.business;

import com.example.word.common.annotation.Business;
import com.example.word.common.api.Api;
import com.example.word.common.api.Pagination;
import com.example.word.common.domain.statistics.business.StatisticsBusiness;
import com.example.word.common.domain.statistics.model.StatisticsId;
import com.example.word.common.domain.statistics.model.StatisticsResponse;
import com.example.word.common.domain.user.business.UserBusiness;
import com.example.word.common.domain.user.model.User;
import com.example.word.common.domain.user.service.UserConverter;
import com.example.word.common.domain.word.model.*;
import com.example.word.common.domain.word.service.WordConverter;
import com.example.word.common.domain.word.service.WordService;
import com.example.word.common.error.ErrorCode;
import com.example.word.common.error.UserErrorCode;
import com.example.word.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Business
@RequiredArgsConstructor
public class WordBusiness {

    private final WordService wordService;
    private final WordConverter wordConverter;

    private final UserBusiness userBusiness;
    private final UserConverter userConverter;

    private final StatisticsBusiness statisticsBusiness;

    @Value("${x.naver.client.id}")
    private String clientId;
    @Value("${x.naver.client.secret}")
    private String clientSecret;

    @Transactional
    public WordResponse saveWord(Api<WordSaveRequest> wordRequest, User user) {

        var wordData = wordRequest.getBody();

        if (Objects.isNull(wordData) || wordData.getWord() == null || wordData.getMean() == null) {
            throw new ApiException(ErrorCode.SERVER_ERROR);
        }

        var word = wordData.getWord();
        var mean = wordData.getMean();
        var memo = wordData.getMemo();

        var userEntity = userBusiness.findByUserWithThrow(user);

        var wordEntity = wordService.wordSave(word, mean, userEntity, memo);

        statisticsBusiness.create(wordEntity, userEntity);

        return wordConverter.toResponse(wordEntity);
    }

    @Transactional
    public WordResponse updateWord(Api<WordUpdateRequest> wordRequest, User user) {
        var wordData = wordRequest.getBody();

        System.out.println(wordData);
        if (Objects.isNull(wordData) || wordData.getWord() == null || wordData.getMean() == null || Objects.isNull(user)) {
            throw new ApiException(ErrorCode.NULL_POINT);
        }

        var optionalWordEntity = wordService.getWordByWordId(wordData.getWordId());

        if (optionalWordEntity.isEmpty()) {
            throw new ApiException(ErrorCode.NULL_POINT);
        }

        var updateWord = wordData.getWord();
        var updateMean = wordData.getMean();
        var updateMemo = wordData.getMemo();

        var optionalWord = wordService.getWordByWordId(wordData.getWordId());


        if (optionalWord.isEmpty()) {
            throw new ApiException(ErrorCode.NULL_POINT);
        }

        var updateWordEntity = wordService.updateWord(optionalWord.get(), updateWord, updateMean, updateMemo);

        statisticsBusiness.updateStatistics(updateWordEntity);

        return wordConverter.toResponse(updateWordEntity);
    }


    public Api<List<WordResponse>> getWordList(User user, Pageable pageable, String sortBy, String order) {
        if (Objects.isNull(user)) {
            throw new ApiException(ErrorCode.SERVER_ERROR);
        }

        var userId = user.getUserId();

        var wordPage = wordService.getWordList(userId, pageable, sortBy, order);

        var wordResponses = wordPage.getContent().stream()
                .map(wordConverter::toResponse)
                .toList();

        var pagination = Pagination.builder()
                .curPage(wordPage.getNumber())
                .curElement(wordPage.getNumberOfElements())
                .size(wordPage.getSize())
                .totalPage(wordPage.getTotalPages())
                .totalElement(wordPage.getTotalElements())
                .sortBy(sortBy)
                .order(order)
                .build();

        return Api.OK(wordResponses, pagination);
    }



    @Transactional
    public void deleteWord(Api<WordDeleteRequest> wordIdApi, User user) {

        if (Objects.isNull(user) || Objects.isNull(wordIdApi)) {
            throw new ApiException(UserErrorCode.DO_NOT_LOGIN);
        }

        var wordId = wordIdApi.getBody().getWordId();
        var userId = user.getUserId();

        wordService.deleteWord(wordId, userId);

        statisticsBusiness.delete(StatisticsId.builder()
                        .wordId(wordId)
                        .userId(userId)
                        .build());

    }


    public List<WordResponse> getWordQuizList(User user, int size) {
        var statisticsList = statisticsBusiness.getWordQuizList(user, size);

        var wordIdList = statisticsList.stream()
                .map(StatisticsResponse::getWordId)
                .toList()
                ;


        return wordService.getWordList(wordIdList).stream()
                .map(wordConverter::toResponse)
                .toList();
    }


    public List<DictionaryResponse> searchDictionary(String word) {
        // 네이버 API URL
        String url = "https://openapi.naver.com/v1/search/webkr.json?query=" + word + "뜻&display="+3;

        // 헤더 설정 (Client ID와 Secret 포함)
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        // 요청 생성
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // RestTemplate로 요청 보내기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NaverApiResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                NaverApiResponse.class
        );

        // 응답 처리
        if (response.getStatusCode() == HttpStatus.OK) {
            return wordConverter.convertToDictionaryResponse(response.getBody());
        } else {
            throw new RuntimeException("네이버 API 요청 실패: " + response.getStatusCode());
        }
    }


}
