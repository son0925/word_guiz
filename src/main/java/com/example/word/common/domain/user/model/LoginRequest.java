package com.example.word.common.domain.user.model;


import com.example.word.common.annotation.Password;
import com.example.word.common.annotation.UserId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginRequest {

    @UserId
    private String userId;

    @Password
    private String password;

}
