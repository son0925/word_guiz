package com.example.word.common.domain.word.service;

import com.example.word.common.domain.user.db.UserRepository;
import com.example.word.common.domain.word.db.WordRepository;
import com.example.word.common.domain.word.model.WordDto;
import com.example.word.common.domain.statistics.service.StatisticsService;
import com.example.word.common.domain.user.db.UserEntity;
import com.example.word.common.domain.word.db.WordEntity;
import com.example.word.common.domain.word.model.WordInsertRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;
    private final UserRepository userRepository;
    private final WordConverter wordConverter;
    private final StatisticsService statisticsService;

    @Transactional
    public WordDto insert(WordInsertRequest wordInsertRequest, String cookie) {
        var user = getUserByCookie(cookie);

        var userId = user.getUserId();
        var word = wordInsertRequest.getWord().trim().toLowerCase();
        var mean = wordInsertRequest.getMean();

        var foundWord = wordRepository.findByWordAndUserId(word, userId);

        if (foundWord.isPresent()) {
            throw new RuntimeException("이미 해당 단어는 존재합니다.");
        }

        var wordEntity = WordEntity.builder()
                .userId(userId)
                .word(word)
                .mean(mean)
                .build();



        wordRepository.save(wordEntity);

        statisticsService.insertWord(wordEntity);

        return wordConverter.toDto(wordEntity);

    }

    public List<WordDto> wordList(String cookie) {

        var user = getUserByCookie(cookie);


        var wordEntityList = wordRepository.findByUserId(user.getUserId());

        return wordEntityList.stream()
                .map(wordConverter::toDto)
                .toList()
                ;
    }

    @Transactional
    public void removeWord(long id, String cookie) {

        var user = getUserByCookie(cookie);

        var optionalWord = wordRepository.findById(id);

        if (optionalWord.isEmpty()) {
            throw new RuntimeException("존재하지 않는 단어입니다.");
        }

        var word = optionalWord.get();

        if (!user.getUserId().equals(word.getUserId())) {
            throw new RuntimeException("생성한 유저가 아닙니다.");
        }

        wordRepository.deleteById(id);
    }

    @Transactional
    public WordDto updateWord(Long wordId, WordInsertRequest wordInsertRequest, String cookie) {

        var user = getUserByCookie(cookie);

        var optionalWord = wordRepository.findById(wordId);

        if (optionalWord.isEmpty()) {
            throw new RuntimeException("단어를 찾을 수 없습니다");
        }

        var word = optionalWord.get();

        if (!word.getUserId().equals(user.getUserId())) {
            throw new RuntimeException("생성한 유저가 아닙니다.");
        }

        word.setWord(wordInsertRequest.getWord().trim().toLowerCase());
        word.setMean(wordInsertRequest.getMean());

        wordRepository.save(word);

        return wordConverter.toDto(word);
    }


    public UserEntity getUserByCookie(String cookie) {
        if (cookie.isEmpty()) {
            throw new RuntimeException("로그인을 먼저 해주세요");
        }

        var optionalUser = userRepository.findById(cookie);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("로그인을 해주세요");
        }

        return optionalUser.get();
    }
}
