package com.example.word.common.domain.statistics.controller;

import com.example.word.common.domain.statistics.service.StatisticsService;
import com.example.word.common.domain.word.model.WordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsApiController {

    private final StatisticsService statisticsService;

    @GetMapping("/get_quiz")
    public List<WordDto> getQuizList(
            @CookieValue(value = "USER", required = false)
            String cookie
    ) {
        return statisticsService.getQuizWord(cookie);
    }
}
