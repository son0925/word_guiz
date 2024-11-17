package com.example.word.common.domain.statistics.business;

import com.example.word.common.annotation.Business;
import com.example.word.common.domain.python.PythonService;
import com.example.word.common.domain.statistics.model.StatisticsEntity;
import com.example.word.common.domain.statistics.model.StatisticsId;
import com.example.word.common.domain.statistics.model.StatisticsResponse;
import com.example.word.common.domain.statistics.model.StatisticsUpdateRequest;
import com.example.word.common.domain.statistics.service.StatisticsConverter;
import com.example.word.common.domain.statistics.service.StatisticsService;
import com.example.word.common.domain.user.business.UserBusiness;
import com.example.word.common.domain.user.model.User;
import com.example.word.common.domain.user.model.UserEntity;
import com.example.word.common.domain.user.service.UserConverter;
import com.example.word.common.domain.word.model.WordEntity;
import com.example.word.common.error.ErrorCode;
import com.example.word.common.exception.ApiException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@Business
@RequiredArgsConstructor
public class StatisticsBusiness {

    private final StatisticsService statisticsService;

    private final StatisticsConverter statisticsConverter;

    private final UserBusiness userBusiness;

    private final UserConverter userConverter;

    private final PythonService pythonService;


    public void create(WordEntity word, UserEntity user) {
        statisticsService.create(word, user);
    }

    public List<StatisticsResponse> read(User user) {
        var userId = user.getUserId();

        var entityList = statisticsService.getStatisticsList(userId);

        return entityList.stream()
                .map(statisticsConverter::toResponse)
                .toList();
    }

    public void updateStatistics(WordEntity wordEntity) {

        var userEntity = wordEntity.getUser();

        statisticsService.updateStatistics(wordEntity, userEntity);

    }

    public void delete(StatisticsId id) {
        statisticsService.deleteWord(id);
    }


    public List<StatisticsResponse> getWordQuizList(User user, int size) {
        var userId = user.getUserId();

        return statisticsService.getWordQuizList(userId, size).stream()
                .map(statisticsConverter::toResponse)
                .toList();
    }

    public void resultUpdate(User user, List<StatisticsUpdateRequest> req) {
        var userId = user.getUserId();

        statisticsService.resultUpdate(userId, req);
    }

    public StatisticsEntity getStatistics(WordEntity wordEntity, UserEntity userEntity) {

        var optionalStatisticsEntity = statisticsService.getStatisticsEntity(wordEntity, userEntity);

        if (optionalStatisticsEntity.isEmpty()) {
            throw new ApiException(ErrorCode.NULL_POINT);
        }
        return optionalStatisticsEntity.get();
    }


    public String getStatisticsList(User user) throws IOException {

        var userEntity = userBusiness.findByUserWithThrow(user);

        var statisticsList = statisticsService.getStatisticsList(userEntity.getUserId());

        var output = pythonService.getGraph(statisticsList);

        return output;
    }
}
