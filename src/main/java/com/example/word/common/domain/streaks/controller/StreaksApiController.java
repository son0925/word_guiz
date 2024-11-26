package com.example.word.common.domain.streaks.controller;

import com.example.word.common.annotation.UserSession;
import com.example.word.common.api.Api;
import com.example.word.common.domain.streaks.business.StreaksBusiness;
import com.example.word.common.domain.streaks.model.StreaksResponse;
import com.example.word.common.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/streaks")
public class StreaksApiController {

    private final StreaksBusiness streaksBusiness;

    @GetMapping("/list")
    public Api<List<StreaksResponse>> getStreaksList(
            @UserSession
            User user
    ) {
        var response = streaksBusiness.getStreaksList(user);

        return Api.OK(response);
    }

}
