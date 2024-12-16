package com.example.word.common.domain.comment.service;

import com.example.word.common.annotation.Converter;
import com.example.word.common.domain.comment.model.ReplyEntity;
import com.example.word.common.domain.comment.model.ReplyResponse;

@Converter
public class ReplyConverter {

    public ReplyResponse toResponse(ReplyEntity entity) {
        return ReplyResponse.builder()
                .replyId(entity.getReplyId())
                .boardId(entity.getBoardId())
                .userId(entity.getUserId())
                .comment(entity.getComment())
                .status(entity.getStatus())
                .createAt(entity.getCreateAt())
                .build();
    }

}
