package com.example.word.common.domain.statistics.db;

import com.example.word.common.domain.statistics.model.StatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StatisticsRepository extends JpaRepository<StatisticsEntity,Long> {

    // 존재하는지 유무
    boolean existsByWordIdAndUserId(Long wordId, String userId);

    // 해당 유저의 통계 보내기
    List<StatisticsEntity> findAllByUserId(String userId);

    // word 삭제할 때 같이 삭제
    void deleteByIdAndUserId(Long wordId, String userId);

    Optional<StatisticsEntity> findAllByUserIdAndWordId(String userId, Long wordId);

    List<StatisticsEntity> findAllByUserIdAndWordIdNotIn(String userId, List<Long> wordIds);

}
