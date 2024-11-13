package com.example.word.common.domain.user.model;

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

    private LocalDateTime birthdate;

    private LocalDateTime lastLoginTime;

    private String profileUrl;
}
