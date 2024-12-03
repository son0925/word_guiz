package com.example.word.common.domain.comment.service;

import com.example.word.common.annotation.Converter;
import com.example.word.common.domain.comment.model.CommentEntity;
import com.example.word.common.domain.comment.model.CommentResponse;

@Converter
public class CommentConverter {

    public CommentResponse toResponse(CommentEntity entity) {
        return CommentResponse.builder()

                .build();
    }

}
