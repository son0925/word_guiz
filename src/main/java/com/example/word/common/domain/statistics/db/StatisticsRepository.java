package com.example.word.common.domain.statistics.db;

import com.example.word.common.domain.statistics.model.PivotResponse;
import com.example.word.common.domain.statistics.model.StatisticsEntity;
import com.example.word.common.domain.statistics.model.StatisticsId;
import com.example.word.common.domain.statistics.model.StatisticsResponse;
import com.example.word.common.domain.user.model.UserEntity;
import com.example.word.common.domain.word.model.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StatisticsRepository extends JpaRepository<StatisticsEntity, StatisticsId> {

    // 존재하는지 유무 확인 (복합 키 StatisticsId 사용)
    boolean existsById(StatisticsId id);

    // 해당 유저의 모든 통계 조회
    List<StatisticsEntity> findAllByIdUserId(String userId);

    // 특정 wordId와 userId로 삭제 (복합 키 사용)
    void deleteById(StatisticsId statisticsId);

    Optional<StatisticsEntity> findByIdUserId(String userId);

    // 특정 userId와 wordId로 조회
    Optional<StatisticsEntity> findByIdUserIdAndIdWordId(String userId, Long wordId);

    // userId와 주어진 wordIds에 포함되지 않은 통계 목록 조회
    List<StatisticsEntity> findAllByIdUserIdAndIdWordIdNotIn(String userId, List<Long> wordIds);

    Optional<StatisticsEntity> findByWordAndUser(WordEntity wordEntity, UserEntity userEntity);

    @Query("SELECT new com.example.word.common.domain.statistics.model.PivotResponse(w.word, s.correctAnswerCount, s.totalQuizCount) " +
            "FROM StatisticsEntity s " +
            "JOIN s.word w " +
            "WHERE s.user.id = :userId")
    List<PivotResponse> findStatisticsByUserId(@Param("userId") String userId);
}
