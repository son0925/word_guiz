package com.example.word.common.domain.comment.model;

import com.example.word.common.domain.comment.model.enums.ReplyStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "reply")
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int replyId;

    private int boardId;

    private String userId;

    private String comment;

    private LocalDateTime createAt;

    @Enumerated(EnumType.STRING)
    private ReplyStatus status;

}
