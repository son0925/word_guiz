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
            UserDto userDto
    ) {
        return userService.register(userDto);
    }


    @PostMapping("/login")
    public Api<UserDto> login(
            @RequestBody
            LoginRequest loginRequest,
            HttpServletResponse httpServletResponse
    ) {
        return userService.login(loginRequest, httpServletResponse);
    }

    @PostMapping("/logout")
    public void logout(
            HttpServletResponse httpServletResponse
    ) {
        userService.logout(httpServletResponse);
    }

}
