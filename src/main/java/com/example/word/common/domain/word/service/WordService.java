package com.example.word.common.domain.word.service;

import com.example.word.common.domain.user.db.UserRepository;
import com.example.word.common.domain.word.db.WordRepository;
import com.example.word.common.domain.word.model.WordDto;
import com.example.word.common.domain.statistics.service.StatisticsService;
import com.example.word.common.domain.user.db.UserEntity;
import com.example.word.common.domain.word.db.WordEntity;
import com.example.word.common.domain.word.model.WordInsertRequest;
import com.example.word.common.error.ErrorCode;
import com.example.word.common.error.UserErrorCode;
import com.example.word.common.error.WordErrorCode;
import com.example.word.common.exception.ApiException;
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
            throw new ApiException(WordErrorCode.BAD_REQUEST_WORD, "해당 단어는 존재합니다.");
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
            throw new ApiException(WordErrorCode.WORD_NOT_FOUND);
        }

        var word = optionalWord.get();

        if (!user.getUserId().equals(word.getUserId())) {
            throw new ApiException(UserErrorCode.USER_NOT_FOUND);
        }

        wordRepository.deleteById(id);
    }

    @Transactional
    public WordDto updateWord(Long wordId, WordInsertRequest wordInsertRequest, String cookie) {

        var user = getUserByCookie(cookie);

        var optionalWord = wordRepository.findById(wordId);

        if (optionalWord.isEmpty()) {
            throw new ApiException(WordErrorCode.WORD_NOT_FOUND);
        }

        var word = optionalWord.get();

        if (!word.getUserId().equals(user.getUserId())) {
            throw new ApiException(UserErrorCode.USER_NOT_FOUND);
        }

        word.setWord(wordInsertRequest.getWord().trim().toLowerCase());
        word.setMean(wordInsertRequest.getMean());

        wordRepository.save(word);

        return wordConverter.toDto(word);
    }


    public UserEntity getUserByCookie(String cookie) {
        if (cookie.isEmpty()) {
            throw new ApiException(UserErrorCode.DO_NOT_LOGIN);
        }

        var optionalUser = userRepository.findById(cookie);

        if (optionalUser.isEmpty()) {
            throw new ApiException(UserErrorCode.DO_NOT_LOGIN);
        }

        return optionalUser.get();
    }
}
