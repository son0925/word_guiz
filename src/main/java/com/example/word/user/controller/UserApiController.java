package com.example.word.user.controller;

import com.example.word.user.model.LoginRequest;
import com.example.word.user.model.UserDto;
import com.example.word.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserApiController  {

    private final UserService userService;


    @PostMapping("/register")
    public UserDto register(
            @RequestBody
            UserDto userDto
    ) {
        return userService.register(userDto);
    }


    @PostMapping("/login")
    public UserDto login(
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
