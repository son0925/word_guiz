package com.example.word.common.domain.user.model;

import com.example.word.common.annotation.Password;
import com.example.word.common.annotation.UserId;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserRegisterRequest {

    @UserId
    private String userId;

    @Password
    private String password;

    @NotBlank(message = "Name은 필수입니다.")
    private String name;

    @NotBlank(message = "생일 정보는 필수입니다.")
    private String birthdate;

}
