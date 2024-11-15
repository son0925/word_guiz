package com.example.word.common.domain.word.model;

import com.example.word.common.domain.statistics.model.StatisticsEntity;
import com.example.word.common.domain.user.model.UserEntity;
import jakarta.persistence.*;
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
@Entity
@Table(name = "word")
public class WordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wordId;

    @Column(nullable = false, length = 45)
    private String word;

    @Column(nullable = false, length = 100)
    private String mean;

    @Column(name = "added_date")
    private LocalDateTime addedDate;

    @Column(name = "memo")
    private String memo;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StatisticsEntity> statistics;

}
