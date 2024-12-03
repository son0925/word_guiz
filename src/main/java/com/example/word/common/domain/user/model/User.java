package com.example.word.common.domain.user.model;

import com.example.word.common.domain.user.model.enums.UserRole;
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

    private UserRole role;

}
