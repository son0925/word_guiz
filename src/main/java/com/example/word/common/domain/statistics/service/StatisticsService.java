package com.example.word.common.domain.statistics.service;

import com.example.word.common.domain.statistics.db.StatisticsRepository;
import com.example.word.common.domain.statistics.model.StatisticsEntity;
import com.example.word.common.domain.user.db.UserRepository;
import com.example.word.common.domain.word.db.WordRepository;
import com.example.word.common.domain.word.model.WordResponse;
import com.example.word.common.error.StatisticsErrorCode;
import com.example.word.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;


    // 생성
    public void create(Long wordId, String userId) {

        // 이미 저장된 단어라면 Error 처리
        if (statisticsRepository.existsByWordIdAndUserId(wordId, userId)) {
            throw new ApiException(StatisticsErrorCode.SAVE_FAILED);
        }

        var statisticsEntity = StatisticsEntity.builder()
                .userId(userId)
                .wordId(wordId)
                .lastQuizDate(null)
                .correctAnswerCount(0)
                .totalQuizCount(0L)
                .noQuizCount(0)
                .build();

        statisticsRepository.save(statisticsEntity);
    }


    // TODO 읽기
    // 특정 사용자가 현재 자신의 통계를 요청
    public List<StatisticsEntity> getStatisticsList(String userId) {
        return statisticsRepository.findAllByUserId(userId);
    }


    // TODO 수정
    public StatisticsEntity update(Long wordId, String userId) {

        var statisticsEntity = StatisticsEntity.builder()
                .userId(userId)
                .wordId(wordId)
                .lastQuizDate(null)
                .correctAnswerCount(0)
                .totalQuizCount(0L)
                .noQuizCount(0)
                .build();

        return statisticsRepository.save(statisticsEntity);
    }

    // TODO 삭제
    public void deleteWord(Long wordId, String userId) {
        statisticsRepository.deleteByIdAndUserId(wordId, userId);
    }


}
