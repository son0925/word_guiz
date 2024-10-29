package com.example.word.statistics.service;

import com.example.word.crud.CRUDAbstractService;
import com.example.word.statistics.db.StatisticsEntity;
import com.example.word.statistics.db.StatisticsRepository;
import com.example.word.statistics.model.StatisticsDto;
import com.example.word.user.db.UserRepository;
import com.example.word.word.db.WordEntity;
import com.example.word.word.db.WordRepository;
import com.example.word.word.model.WordDto;
import com.example.word.word.service.WordConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final WordRepository wordRepository;
    private final UserRepository userRepository;
    private final WordConverter wordConverter;

    public void insertWord(WordEntity wordEntity) {

        var statisticsEntity = StatisticsEntity.builder()
                .wordId(wordEntity.getWordId())
                .userId(wordEntity.getUserId())
                .quizYn(0)
                .lastQuizDate(LocalDateTime.now())
                .correctAnswerCount(0)
                .cnt(0)
                .build()
                ;

        statisticsRepository.save(statisticsEntity);

    }

    public List<WordDto> getQuizWord(String cookie) {

        return wordRepository.findAllByUserId(cookie)
                .stream()
                .map(wordConverter::toDto)
                .toList()
                ;
    }
}
