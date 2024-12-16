package com.example.word.common.domain.board.service;

import com.example.word.common.annotation.Converter;
import com.example.word.common.domain.board.model.BoardEntity;
import com.example.word.common.domain.board.model.BoardResponse;
import com.example.word.common.domain.comment.business.ReplyBusiness;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class BoardConverter {

    private final ReplyBusiness commentBusiness;

    public BoardResponse toResponse(BoardEntity entity) {
        return BoardResponse.builder()
                .boardId(entity.getBoardId())
                .userId(entity.getUserId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .status(entity.getStatus())
                .password(entity.getPassword())
                .createAt(entity.getCreateAt())
                .boardLike(entity.getBoardLike())
                .visitCount(entity.getVisitCount())
                .repliesList(commentBusiness.getCommentList(entity.getBoardId()))
                .build()
                ;
    }

}
