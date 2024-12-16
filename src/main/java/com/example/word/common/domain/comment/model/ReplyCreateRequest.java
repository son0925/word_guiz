package com.example.word.common.domain.comment.model;

import com.example.word.common.domain.comment.model.enums.ReplyStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyCreateRequest {

    private int boardId;

    private String comment;

}
