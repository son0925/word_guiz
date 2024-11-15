package com.example.word.common.domain.user.model;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

}
