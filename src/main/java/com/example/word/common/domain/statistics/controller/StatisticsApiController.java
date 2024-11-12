package com.example.word.common.domain.statistics.controller;

import com.example.word.common.annotation.UserSession;
import com.example.word.common.api.Api;
import com.example.word.common.domain.statistics.business.StatisticsBusiness;
import com.example.word.common.domain.statistics.model.StatisticsResponse;
import com.example.word.common.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsApiController {

    private final StatisticsBusiness statisticsBusiness;

    @GetMapping("/list")
    public Api<List<StatisticsResponse>> read(
            @UserSession
            User user
    ) {
        var response = statisticsBusiness.read(user);

        return Api.OK(response);
    }

}
