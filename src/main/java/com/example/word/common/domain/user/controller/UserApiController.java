package com.example.word.common.domain.user.controller;

import com.example.word.common.annotation.UserSession;
import com.example.word.common.api.Api;
import com.example.word.common.domain.user.business.UserBusiness;
import com.example.word.common.domain.user.model.ChangePasswordRequest;
import com.example.word.common.domain.user.model.User;
import com.example.word.common.domain.user.model.UserResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.util.PythonInterpreter;
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
        return Api.OK("");
    }

    @PostMapping("/change-password")
    public Api<String> passwordChange(
            @UserSession User user,
            @RequestBody @Valid Api<ChangePasswordRequest> req
    ) {
        userBusiness.changePassword(req, user);

        return Api.OK("비밀번호가 성공적으로 변경되었습니다.");
    }

    @DeleteMapping("/delete")
    public Api<String> deleteUser(
            @UserSession User user
    ) {
        userBusiness.deleteUser(user);
        return Api.OK("회원이 비활성화 되었습니다.");
    }

    @PostMapping("/logout")
    public Api<String> logout(
            @UserSession
            User user,
            HttpServletResponse response
    ) {
        userBusiness.logout(user, response);

        return Api.OK("로그아웃 되었습니다.");
    }

}
