package com.example.word.common.domain.user.model;

import com.example.word.common.annotation.Password;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {

    @Password
    private String currentPassword;

    @Password
    private String changePassword;

}
