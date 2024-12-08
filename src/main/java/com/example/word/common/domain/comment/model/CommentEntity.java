package com.example.word.common.domain.comment.model;

import com.example.word.common.domain.comment.model.enums.CommentStatus;
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
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    private int boardId;

    private String userId;

    private String comment;

    private LocalDateTime createAt;

    @Enumerated(EnumType.STRING)
    private CommentStatus status;

}
