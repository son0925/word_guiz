package com.example.word.common.domain.board.db;

import com.example.word.common.domain.board.model.BoardEntity;
import com.example.word.common.domain.board.model.enums.BoardStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    List<BoardEntity> findAllByUserIdAndStatusNot(String writerId, BoardStatus status);

    List<BoardEntity> findAllByTitleContainingAndStatusNot(String keyword, BoardStatus status);

    Page<BoardEntity> findAllByStatusNot(BoardStatus status, Pageable pageable);
}
