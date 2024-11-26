package com.example.word.common.domain.streaks.db;

import com.example.word.common.domain.streaks.model.StreaksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StreaksRepository extends JpaRepository<StreaksEntity, StreaksEntity.StreaksId> {

    List<StreaksEntity> findByUserId(String userId);

}
