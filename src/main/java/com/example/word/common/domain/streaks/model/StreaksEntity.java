package com.example.word.common.domain.streaks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private String userId; // 사용자 ID

    @Id
    private LocalDate date; // 활동 날짜

    @Setter
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
