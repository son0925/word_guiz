package com.example.word.common.domain.user.controller;

import com.example.word.common.api.Api;
import com.example.word.common.domain.user.model.LoginRequest;
import com.example.word.common.domain.user.model.UserDto;
import com.example.word.common.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserApiController  {

    private final UserService userService;


    @PostMapping("/register")
    public Api<UserDto> register(
            @RequestBody
            Api<UserDto> userDto
    ) {
        var dto = userService.register(userDto.getBody());
        return Api.OK(dto);
    }


    @PostMapping("/login")
    public Api<UserDto> login(
            @RequestBody
            Api<LoginRequest> loginRequest,
            HttpServletResponse httpServletResponse
    ) {
        var userDto = userService.login(loginRequest.getBody(), httpServletResponse);
        return Api.OK(userDto);
    }

    @PostMapping("/logout")
    public Api<String> logout(
            HttpServletResponse httpServletResponse
    ) {
        userService.logout(httpServletResponse);
        return Api.OK("로그아웃 되었습니다.");
    }

}
