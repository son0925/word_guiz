package com.example.word.common.domain.word.service;

import com.example.word.common.domain.user.business.UserBusiness;
import com.example.word.common.domain.user.model.User;
import com.example.word.common.domain.user.model.UserEntity;
import com.example.word.common.domain.word.db.WordRepository;
import com.example.word.common.domain.word.model.WordEntity;
import com.example.word.common.domain.word.model.WordUpdateRequest;
import com.example.word.common.error.ErrorCode;
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

    private final UserBusiness userBusiness;


    public WordEntity wordSave(String word, String mean, UserEntity user, String memo) {

        var existsWord = wordRepository.findByWordAndUser(word, user);

        if (existsWord.isPresent()) {
            throw new ApiException(WordErrorCode.EXISTS_WORD);
        }

        var wordEntity = WordEntity.builder()
                .word(word)
                .mean(mean)
                .addedDate(LocalDateTime.now())
                .memo(memo)
                .user(user)
                .build()
                ;

        return wordRepository.save(wordEntity);
    }

    public WordEntity updateWord(WordEntity wordEntity, WordUpdateRequest word, User user) {

        var userEntity = userBusiness.findByUserWithThrow(user);

        // 변경할려는 단어가 이미 DB 에 존재하고 원래 기존의 단어와 같지 않을 때(뜻, 메모만 수정하는 경우도 있기 때문)
        if (wordRepository.existsByWordAndUser(word.getWord(), userEntity) && !wordEntity.getWord().equals(word.getWord())) {
            throw new ApiException(WordErrorCode.EXISTS_WORD);
        }

        wordEntity.setWord(word.getWord());
        wordEntity.setMean(word.getMean());
        wordEntity.setMemo(word.getMemo());

        return wordRepository.save(wordEntity);

    }


    public Page<WordEntity> getWordList(String userId, Pageable pageable, String sortBy, String order, String searchWord) {

        Sort sort = Sort.by(Sort.Order.desc(sortBy));
        if ("asc".equalsIgnoreCase(order)) {
            sort = Sort.by(Sort.Order.asc(sortBy));
        }

        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return wordRepository.findAllByUser_UserIdAndWordStartingWith(userId,searchWord, sortedPageable);
    }

    public void deleteWord(Long wordId, String userId) {
        wordRepository.deleteByWordIdAndUser_UserId(wordId, userId);
    }


    public List<WordEntity> getWordList(List<Long> wordIdList) {
        return wordRepository.findByWordIdIn(wordIdList);
    }

    public WordEntity getWordByWordIdWithThrow(Long wordId) {
        return wordRepository.findByWordId(wordId)
                .orElseThrow(() -> new ApiException(ErrorCode.BAD_REQUEST));
    }


    public WordEntity findByIdWithThrow(Long wordId) {
        return wordRepository.findByWordId(wordId)
                .orElseThrow(() -> new ApiException(ErrorCode.BAD_REQUEST));
    }
}
