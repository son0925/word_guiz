package com.example.word.common.domain.user.controller;

import com.example.word.common.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PageMoveApiController {

    private final UserService userService;

    @GetMapping("")
    public String mainPage(
            Model model,
            @CookieValue(value = "USER", required = false)
            String cookie
    ) {
        model.addAttribute("user", cookie);
        return "main_page";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

}
