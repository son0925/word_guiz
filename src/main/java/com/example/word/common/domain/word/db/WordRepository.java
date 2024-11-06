package com.example.word.common.domain.word.db;

import com.example.word.common.domain.word.model.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WordRepository extends JpaRepository<WordEntity, Long> {

    // 특정 유저 단어 리스트
    List<WordEntity> findAllByUserId(String userId);

    // 특정 유저의 특정 단어 삭제
    void deleteByWordIdAndUserId(Long wordId, String userId);

    // 특정 유저의 특정 단어가 존재하는지(단어 추가시)
    Optional<WordEntity> findByWordAndUserId(String word, String userId);


}
