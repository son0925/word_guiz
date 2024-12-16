package com.example.word.common.domain.comment.db;

import com.example.word.common.domain.comment.model.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Integer> {

    List<ReplyEntity> findAllByBoardId(int boarId);

}
