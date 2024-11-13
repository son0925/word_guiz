package com.example.word.common.domain.word.service;

import com.example.word.common.domain.word.db.WordRepository;
import com.example.word.common.domain.word.model.WordEntity;
import com.example.word.common.error.ErrorCode;
import com.example.word.common.error.WordErrorCode;
import com.example.word.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;


    public WordEntity wordSave(String word, String mean, String userId) {

        if (word == null || mean == null || userId == null) {
            throw new ApiException(ErrorCode.SERVER_ERROR);
        }

        var existsWord = wordRepository.findByWordAndUserId(word, userId);

        if (existsWord.isPresent()) {
            throw new ApiException(WordErrorCode.EXISTS_WORD);
        }

        var wordEntity = WordEntity.builder()
                .word(word)
                .mean(mean)
                .userId(userId)
                .addedDate(LocalDateTime.now())
                .build()
                ;

        return wordRepository.save(wordEntity);
    }

    public WordEntity wordUpdate(Long wordId, String word, String mean, String userId) {

        if (wordId == null || word == null || mean == null || userId == null) {
            throw new ApiException(ErrorCode.SERVER_ERROR);
        }

        var existsWord = wordRepository.findByWordAndUserId(word, userId);

        // 이미 해당 단어는 존재하며 wordId 가 다를 때
        if (existsWord.isPresent() && !existsWord.get().getWordId().equals(wordId)) {
            throw new ApiException(WordErrorCode.EXISTS_WORD, "바꾸시는 단어는 이미 단어에 존재합니다.");
        }

        var wordEntity = WordEntity.builder()
                .wordId(wordId)
                .word(word)
                .mean(mean)
                .userId(userId)
                .addedDate(LocalDateTime.now())
                .build()
                ;

        return wordRepository.save(wordEntity);
    }

    public Page<WordEntity> getWordList(String userId, Pageable pageable) {
        return wordRepository.findAllByUserId(userId, pageable);
    }

    public void deleteWord(Long wordId, String userId) {
        wordRepository.deleteByWordIdAndUserId(wordId, userId);
    }
}
