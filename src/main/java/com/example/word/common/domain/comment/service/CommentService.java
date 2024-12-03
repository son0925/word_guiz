package com.example.word.common.domain.comment.service;

import com.example.word.common.domain.comment.db.CommentRepository;
import com.example.word.common.domain.comment.model.CommentEntity;
import com.example.word.common.domain.comment.model.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<CommentEntity> getCommentList(int boardId) {
        return commentRepository.findAllByBoardId(boardId);
    }
}
