package com.example.word.common.domain.board.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardUpdateRequest {

    private int boardId;

    private String userId;

    private String title;

    private String content;

    private String password;



}
