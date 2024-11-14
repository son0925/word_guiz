package com.example.word.common.domain.word.db;

import com.example.word.common.domain.user.model.UserEntity;
import com.example.word.common.domain.word.model.WordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WordRepository extends JpaRepository<WordEntity, Long> {

    // 특정 유저의 특정 단어 삭제
    void deleteByWordIdAndUser_UserId(Long wordId, String userId);

    // 특정 유저의 특정 단어가 존재하는지(단어 추가시)
    Optional<WordEntity> findByWordAndUser(String word, UserEntity user);

    Page<WordEntity> findAllByUser_UserId(String userId, Pageable pageable);

    List<WordEntity> findByWordIdIn(List<Long> wordIdList);

    Optional<WordEntity> findByWordId(Long wordId);

}
