package com.example.word.common.domain.board.model;

import com.example.word.common.domain.board.model.enums.BoardStatus;
import com.example.word.common.domain.comment.model.ReplyResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardResponse {

    private Integer boardId;

    private String userId;

    private String title;

    private String content;

    private LocalDateTime createAt;

    private BoardStatus status;

    private String password;

    private int boardLike;

    private int visitCount;

    private List<ReplyResponse> repliesList;

}
