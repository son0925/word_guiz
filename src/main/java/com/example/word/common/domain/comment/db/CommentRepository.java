package com.example.word.common.domain.comment.db;

import com.example.word.common.domain.comment.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    List<CommentEntity> findAllByBoardId(int boarId);

}
