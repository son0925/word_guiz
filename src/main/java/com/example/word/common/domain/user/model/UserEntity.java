package com.example.word.common.domain.user.model;

import com.example.word.common.domain.statistics.model.StatisticsEntity;
import com.example.word.common.domain.user.model.enums.UserRole;
import com.example.word.common.domain.user.model.enums.UserStatus;
import com.example.word.common.domain.word.model.WordEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "user")
public class UserEntity {

    @Id
    private String userId;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private LocalDateTime birthdate;

    private LocalDateTime lastLoginTime;

    private String profileUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WordEntity> words;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StatisticsEntity> statistics;

}
