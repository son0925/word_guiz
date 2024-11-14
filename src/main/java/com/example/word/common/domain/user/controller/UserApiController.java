package com.example.word.common.domain.user.controller;

import com.example.word.common.annotation.UserSession;
import com.example.word.common.api.Api;
import com.example.word.common.domain.user.business.UserBusiness;
import com.example.word.common.domain.user.model.User;
import com.example.word.common.domain.user.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserBusiness userBusiness;



    @GetMapping("/info")
    public Api<UserResponse> info(
            @UserSession
            User user
    ) {
        var response = userBusiness.info(user);

        return Api.OK(response);
    }

    @PostMapping("/upload-profile")
    public Api<String> uploadProfile(
            @RequestPart("profileImage") MultipartFile image,
            @UserSession User user
    ) throws IOException {
        userBusiness.saveProfileUrl(image, user);
        return Api.OK("프로필이 저장되었습니다.");
    }
}
