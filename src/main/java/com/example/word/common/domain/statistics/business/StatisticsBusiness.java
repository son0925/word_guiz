package com.example.word.common.domain.statistics.business;

import com.example.word.common.annotation.Business;
import com.example.word.common.domain.statistics.converter.StatisticsConverter;
import com.example.word.common.domain.statistics.model.StatisticsResponse;
import com.example.word.common.domain.statistics.service.StatisticsService;
import com.example.word.common.domain.user.model.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Business
@RequiredArgsConstructor
public class StatisticsBusiness {

    private final StatisticsService statisticsService;

    private final StatisticsConverter statisticsConverter;


    public void create(Long wordId, String userId) {

        statisticsService.create(wordId, userId);

    }

    public List<StatisticsResponse> read(User user) {
        var userId = user.getUserId();

        var entityList = statisticsService.getStatisticsList(userId);

        return entityList.stream()
                .map(statisticsConverter::toResponse)
                .toList();
    }

    public void update(Long wordId, String userId) {
        var updateEntity = statisticsService.update(wordId, userId);

        statisticsConverter.toResponse(updateEntity);
    }

    public void delete(Long wordId, String userId) {
        statisticsService.deleteWord(wordId, userId);
    }

}
