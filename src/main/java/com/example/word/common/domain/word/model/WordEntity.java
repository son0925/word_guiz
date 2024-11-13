package com.example.word.common.domain.word.model;

import com.example.word.common.domain.user.db.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "word")
public class WordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wordId;

    private String word;

    private String mean;

    private String userId;

    private LocalDateTime addedDate;

}
