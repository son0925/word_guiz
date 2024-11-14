package com.example.word.common.domain.user.model;

import com.example.word.common.domain.user.model.enums.UserStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserResponse {

    private String userId;

    private String password;

    private String name;

    private UserStatus status;

    private LocalDateTime birthdate;

    private LocalDateTime lastLoginTime;

    private String profileUrl;
}
