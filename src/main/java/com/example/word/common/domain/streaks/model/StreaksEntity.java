package com.example.word.common.domain.streaks.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "streaks")
@IdClass(StreaksEntity.StreaksId.class) // 복합키 설정
public class StreaksEntity {

    @Id
    @Column(name = "user_id", nullable = false, length = 20)
    private String userId; // 사용자 ID

    @Id
    @Column(name = "date", nullable = false)
    private LocalDate date; // 활동 날짜

    @Setter
    @Column(name = "problems_solved", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer problemsSolved; // 푼 문제 수 (기본값 0)

    // 복합 키 클래스
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Builder
    public static class StreaksId implements Serializable {
        private String userId;
        private LocalDate date;
    }
}
