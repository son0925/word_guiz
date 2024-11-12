package com.example.word.common.domain.statistics.db;

import com.example.word.common.domain.statistics.model.StatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatisticsRepository extends JpaRepository<StatisticsEntity,Long> {

    boolean existsByWordIdAndUserId(Long wordId, String userId);

    List<StatisticsEntity> findAllByUserId(String userId);

    void deleteByIdAndUserId(Long wordId, String userId);
}
