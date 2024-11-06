package com.example.word.common.domain.user.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserResponse {

    private String userId;

    private String password;

    private String name;

}
