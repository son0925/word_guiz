package com.example.word.common.domain.board.model;

import com.example.word.common.domain.board.model.enums.BoardStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardId;

    private String userId;

    @Setter
    private String title;

    @Setter
    private String content;

    private LocalDateTime createAt;

    @Setter
    @Enumerated(EnumType.STRING)
    private BoardStatus status;

    @Setter
    private String password;

    @Setter
    private int boardLike;

    @Setter
    private int visitCount;

}
