package com.example.word.statistics.controller;

import com.example.word.crud.CRUDAbstractApiController;
import com.example.word.statistics.db.StatisticsEntity;
import com.example.word.statistics.model.StatisticsDto;
import com.example.word.statistics.service.StatisticsService;
import com.example.word.word.model.WordDto;
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
