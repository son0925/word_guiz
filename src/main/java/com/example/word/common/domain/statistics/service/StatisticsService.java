package com.example.word.common.domain.statistics.service;

import com.example.word.common.domain.statistics.db.StatisticsRepository;
import com.example.word.common.domain.statistics.model.StatisticsEntity;
import com.example.word.common.domain.statistics.model.StatisticsUpdateRequest;
import com.example.word.common.domain.statistics.model.enums.StatisticsStatus;
import com.example.word.common.error.ErrorCode;
import com.example.word.common.error.StatisticsErrorCode;
import com.example.word.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;


    public void create(Long wordId, String userId) {

        // 이미 저장된 단어라면 Error 처리
        if (statisticsRepository.existsByWordIdAndUserId(wordId, userId)) {
            throw new ApiException(StatisticsErrorCode.SAVE_FAILED);
        }

        var statisticsEntity = StatisticsEntity.builder()
                .userId(userId)
                .wordId(wordId)
                .status(StatisticsStatus.NO_ANSWER)
                .correctAnswerCount(0)
                .totalQuizCount(0L)
                .noQuizCount(0)
                .build();

        statisticsRepository.save(statisticsEntity);
    }


    // 특정 사용자가 현재 자신의 통계를 요청
    public List<StatisticsEntity> getStatisticsList(String userId) {
        return statisticsRepository.findAllByUserId(userId);
    }


    public StatisticsEntity update(Long wordId, String userId) {

        var entity = statisticsRepository.findAllByUserIdAndWordId(userId, wordId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));

        entity.setNoQuizCount(0);
        entity.setTotalQuizCount(0L);
        entity.setCorrectAnswerCount(0);
        entity.setStatus(StatisticsStatus.NO_ANSWER);

        return statisticsRepository.save(entity);
    }

    public void deleteWord(Long wordId, String userId) {
        statisticsRepository.deleteByIdAndUserId(wordId, userId);
    }

    // 단어 퀴즈 알고리즘

    /**
     * 1. 퀴즈를 한 번도 하지 않은 단어
     * 2. 마지막 퀴즈에서 틀린 단어
     * 3. 20번 이상 연속으로 퀴즈를 하지 않은 단어
     * 4. 정답률이 저조한 단어
     */
    public List<StatisticsEntity> getWordQuizList(String userId, int size) {
        var statisticsList = statisticsRepository.findAllByUserId(userId);

        statisticsList.forEach(it -> System.out.println(it.getWordId()));

        // 퀴즈를 한 번도 하지 않은 단어
        var wordQuizList = statisticsList.stream()
                .filter(it -> it.getStatus().equals(StatisticsStatus.NO_ANSWER))
                .collect(Collectors.toList());

        // size 채웠다면 바로 return 하기
        if (wordQuizList.size() >= size) {
            System.out.println(size + " " + wordQuizList.size() + " 1");
            return wordQuizList.subList(0, size);
        }

        // 마지막 퀴즈에서 틀린 단어를 추가
        statisticsList.stream()
                .filter(it -> it.getStatus().equals(StatisticsStatus.WRONG_ANSWER))
                .filter(it -> !wordQuizList.contains(it))  // 이미 포함된 단어는 제외
                .forEach(wordQuizList::add);

        // size 채웠다면 바로 return 하기
        if (wordQuizList.size() >= size) {
            System.out.println(wordQuizList.size() + " 2");
            return wordQuizList.subList(0, size);
        }

        // 20번 연속으로 퀴즈를 하지 않은 단어
        statisticsList.stream()
                .filter(it -> it.getNoQuizCount() >= 20)
                .filter(it -> !wordQuizList.contains(it))  // 이미 포함된 단어는 제외
                .forEach(wordQuizList::add);

        // size 채웠다면 바로 return 하기
        if (wordQuizList.size() >= size) {
            System.out.println(wordQuizList.size() + " 3");
            return wordQuizList.subList(0, size);
        }

        // 퀴즈를 풀었지만 틀린 단어들 (정답률에 따라 정렬)
        var sortList = new ArrayList<>(statisticsList.stream()
                .filter(it -> !wordQuizList.contains(it))
                .toList());

        // 정답률에 따른 오름차순 정렬
        sortList.sort((o1, o2) -> {
            double o1CorrectRate = o1.getCorrectAnswerCount() / (double) o1.getTotalQuizCount();
            double o2CorrectRate = o2.getCorrectAnswerCount() / (double) o2.getTotalQuizCount();
            return Double.compare(o1CorrectRate, o2CorrectRate);
        });

        // 부족한 개수만큼 퀴즈를 풀었지만 틀린 단어들에서 추가
        int remainingSize = size - wordQuizList.size();
        wordQuizList.addAll(sortList.subList(0, Math.min(remainingSize, sortList.size())));

        return wordQuizList;
    }


    public void resultUpdate(String userId, List<StatisticsUpdateRequest> statisticsList) {
        for (StatisticsUpdateRequest result : statisticsList) {
            updateStatus(userId, result.getWordId(), result.getStatus());
        }

        var wordIdList = statisticsList.stream()
                .map(StatisticsUpdateRequest::getWordId)
                .toList();

        var noAnswerEntities = statisticsRepository.findAllByUserIdAndWordIdNotIn(userId, wordIdList);

        noAnswerEntities.forEach(it -> {
            it.setNoQuizCount(it.getNoQuizCount() + 1);
        });

        statisticsRepository.saveAll(noAnswerEntities);
    }

    public void updateStatus(String userId, Long wordId, StatisticsStatus status) {
        var entity = statisticsRepository.findAllByUserIdAndWordId(userId, wordId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));

        entity.setStatus(status);
        if (StatisticsStatus.ANSWER.equals(status)) {
            entity.setCorrectAnswerCount(entity.getCorrectAnswerCount() + 1);
        }
        entity.setTotalQuizCount(entity.getTotalQuizCount() + 1);

        statisticsRepository.save(entity);
    }
}
