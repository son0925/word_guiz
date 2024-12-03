package com.example.word.common.domain.comment.business;

import com.example.word.common.annotation.Business;
import com.example.word.common.domain.comment.model.CommentResponse;
import com.example.word.common.domain.comment.service.CommentConverter;
import com.example.word.common.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Business
@RequiredArgsConstructor
public class CommentBusiness {

    private final CommentService commentService;

    private final CommentConverter commentConverter;


    // 해당 게시글의 댓글 리스트 가지고 오기
    public List<CommentResponse> getCommentList(int boardId) {

        return commentService.getCommentList(boardId).stream()
                .map(commentConverter::toResponse)
                .toList()
                ;

    }
}
