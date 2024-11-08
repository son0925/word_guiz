package com.example.word.common.domain.statistics.business;

import com.example.word.common.annotation.Business;
import com.example.word.common.domain.statistics.model.StatisticsEntity;
import com.example.word.common.domain.statistics.model.StatisticsResponse;
import com.example.word.common.domain.statistics.service.StatisticsService;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class StatisticsBusiness {

    private final StatisticsService statisticsService;


    // todo create
    public void create(Long wordId, String userId) {

        statisticsService.create(wordId, userId);

    }

    // todo read

    // todo update

    // todo delete

}
