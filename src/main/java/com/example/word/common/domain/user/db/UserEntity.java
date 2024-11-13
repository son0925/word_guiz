package com.example.word.common.domain.user.db;

import com.example.word.common.domain.word.model.WordEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;

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

    private LocalDateTime birthdate;

    private LocalDateTime lastLoginTime;

    private String profileUrl;

}
