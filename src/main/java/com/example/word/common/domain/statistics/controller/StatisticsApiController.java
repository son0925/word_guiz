package com.example.word.common.domain.statistics.controller;

import com.example.word.common.annotation.UserSession;
import com.example.word.common.api.Api;
import com.example.word.common.domain.statistics.business.StatisticsBusiness;
import com.example.word.common.domain.statistics.model.StatisticsResponse;
import com.example.word.common.domain.statistics.model.StatisticsUpdateRequest;
import com.example.word.common.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
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

        System.out.println(req);
        statisticsBusiness.resultUpdate(user, req);

        return Api.OK("업데이트 완료");
    }

}
