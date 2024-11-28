package com.example.word.common.domain.statistics.controller;

import com.example.word.common.annotation.UserSession;
import com.example.word.common.api.Api;
import com.example.word.common.domain.statistics.business.StatisticsBusiness;
import com.example.word.common.domain.statistics.model.PivotResponse;
import com.example.word.common.domain.statistics.model.StatisticsResponse;
import com.example.word.common.domain.statistics.model.StatisticsUpdateRequest;
import com.example.word.common.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
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

    @PutMapping("/result")
    public Api<String> update(
            @UserSession User user,
            @RequestBody List<StatisticsUpdateRequest> req
    ) {
        statisticsBusiness.resultUpdate(user, req);
        return Api.OK("업데이트 완료");
    }

    @GetMapping("/pivot")
    public Api<List<PivotResponse>> pivot(
            @UserSession User user
    ) {
        var response = statisticsBusiness.getPivot(user);

        return Api.OK(response);
    }

    @GetMapping("/getStatistics")
    public Api<String> getGraph(
            @UserSession User user
    ) throws IOException {
        var response = statisticsBusiness.getStatisticsList(user);

        return Api.OK(response);
    }

}
