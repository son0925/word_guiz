package com.example.word.word.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WordRepository extends JpaRepository<WordEntity, Long> {

    List<WordEntity> findByUserId(String userId);

    Optional<WordEntity> findByWord(String word);

    void deleteByWord(String word);

}
