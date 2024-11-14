package com.example.word.common.domain.word.service;

import com.example.word.common.domain.statistics.business.StatisticsBusiness;
import com.example.word.common.domain.user.model.UserEntity;
import com.example.word.common.domain.user.service.UserConverter;
import com.example.word.common.domain.word.db.WordRepository;
import com.example.word.common.domain.word.model.WordEntity;
import com.example.word.common.error.WordErrorCode;
import com.example.word.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;


    public WordEntity wordSave(String word, String mean, UserEntity user) {

        var existsWord = wordRepository.findByWordAndUser(word, user);

        if (existsWord.isPresent()) {
            throw new ApiException(WordErrorCode.EXISTS_WORD);
        }

        var wordEntity = WordEntity.builder()
                .word(word)
                .mean(mean)
                .addedDate(LocalDateTime.now())
                .user(user)
                .build()
                ;

        return wordRepository.save(wordEntity);
    }

    public WordEntity updateWord(WordEntity wordEntity, String updateWord, String updateMean) {

        wordEntity.setWord(updateWord);
        wordEntity.setMean(updateMean);

        return wordRepository.save(wordEntity);

    }


    public Page<WordEntity> getWordList(String userId, Pageable pageable, String sortBy, String order) {

        Sort sort = Sort.by(Sort.Order.desc(sortBy));
        if ("asc".equalsIgnoreCase(order)) {
            sort = Sort.by(Sort.Order.asc(sortBy));
        }

        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return wordRepository.findAllByUser_UserId(userId, sortedPageable);
    }

    public void deleteWord(Long wordId, String userId) {
        wordRepository.deleteByWordIdAndUser_UserId(wordId, userId);
    }


    public List<WordEntity> getWordList(List<Long> wordIdList) {
        return wordRepository.findByWordIdIn(wordIdList);
    }

    public Optional<WordEntity> getWordByWordId(Long wordId) {
        return wordRepository.findByWordId(wordId);
    }


}
