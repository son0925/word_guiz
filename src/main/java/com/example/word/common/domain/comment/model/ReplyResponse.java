package com.example.word.common.domain.comment.model;

import com.example.word.common.domain.comment.model.enums.ReplyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyResponse {

    private int replyId;

    private int boardId;

    private String userId;

    private String comment;

    private LocalDateTime createAt;

    private ReplyStatus status;
}
