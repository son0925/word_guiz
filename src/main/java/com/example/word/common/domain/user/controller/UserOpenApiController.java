package com.example.word.common.domain.user.controller;

import com.example.word.common.api.Api;
import com.example.word.common.domain.token.model.TokenResponse;
import com.example.word.common.domain.user.business.UserBusiness;
import com.example.word.common.domain.user.model.LoginRequest;
import com.example.word.common.domain.user.model.User;
import com.example.word.common.domain.user.model.UserRegisterRequest;
import com.example.word.common.domain.user.model.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserOpenApiController {

    private final UserBusiness userBusiness;

    // 로그인
    @PostMapping("/login")
    public Api<TokenResponse> login(
            @Valid
            @RequestBody
            Api<LoginRequest> user
    ) {
        return Api.OK(userBusiness.login(user));
    }

    // 회원가입
    @PostMapping("/register")
    public Api<UserResponse> register(
            @Valid
            @RequestBody
            Api<UserRegisterRequest> user
    ) {
        return Api.OK(userBusiness.register(user));
    }

}
