package com.example.word.common.domain.user.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {

    private String userId;

    private String password;

    private String name;

}
