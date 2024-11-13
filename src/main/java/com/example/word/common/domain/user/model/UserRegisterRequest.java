package com.example.word.common.domain.user.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserRegisterRequest {

    @NotBlank(message = "User ID는 필수입니다.")
    private String userId;

    @NotBlank(message = "Password는 필수입니다.")
    private String password;

    @NotBlank(message = "Name은 필수입니다.")
    private String name;

    private String birthdate;

}
