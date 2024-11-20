package com.example.word.common.domain.user.business;

import com.example.word.common.annotation.Business;
import com.example.word.common.api.Api;
import com.example.word.common.domain.token.business.TokenBusiness;
import com.example.word.common.domain.user.model.*;
import com.example.word.common.domain.user.model.enums.UserStatus;
import com.example.word.common.domain.user.service.UserConverter;
import com.example.word.common.domain.user.service.UserService;
import com.example.word.common.error.ErrorCode;
import com.example.word.common.exception.ApiException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;

    private final UserConverter userConverter;

    private final TokenBusiness tokenBusiness;


    // 유저 로그인
    public UserResponse login(Api<LoginRequest> req, HttpServletResponse response) {

        var user = req.getBody();

        if (Objects.isNull(user) || user.getUserId() == null || user.getPassword() == null) {
            throw new ApiException(ErrorCode.NULL_POINT);
        }

        var userId = user.getUserId();
        var password = user.getPassword();


        var userEntity = userService.login(userId, password);


        tokenBusiness.cookieSettingToken(userId, response);

        return userConverter.toResponse(userEntity);
    }

    // 유저 회원가입
    public UserResponse register(Api<UserRegisterRequest> req) {

        var user = req.getBody();

        if (Objects.isNull(user) || user.getUserId() == null || user.getPassword() == null|| user.getName() == null) {
            throw new ApiException(ErrorCode.NULL_POINT);
        }

        var userId = user.getUserId();
        var password = user.getPassword();
        var name = user.getName();

        var birthdate = userConverter.convertToLocalDateTime(user.getBirthdate());

        var userEntity = userService.register(userId, password, name, birthdate);

        return userConverter.toResponse(userEntity);

    }

    // ID 중복 체크
    public String existentUserWithThrow(String userId) {
        if (userId.isBlank()) {
            throw new ApiException(ErrorCode.NULL_POINT, "잘못된 요청입니다.");
        }

        return userService.existentUserWithThrow(userId);

    }


    // 휴먼 계정 활성화
    public UserResponse accountActivation(LoginRequest req) {

        if (Objects.isNull(req) || req.getUserId() == null || req.getPassword() == null) {
            throw new ApiException(ErrorCode.NULL_POINT);
        }

        var userId = req.getUserId();
        var password = req.getPassword();

        var response = userService.accountActivation(userId, password);

        return userConverter.toResponse(response);

    }

    public UserEntity findByUserWithThrow(User user) {

        if (Objects.isNull(user) || user.getUserId() == null) {
            throw new ApiException(ErrorCode.NULL_POINT);
        }

        var userId = user.getUserId();

        return userService.findByIdWithThrow(userId);

    }

    public UserResponse info(User user) {

        var userId = user.getUserId();

        var entity = userService.findByIdWithThrow(userId);

        return userConverter.toResponse(entity);

    }


    public void saveProfileUrl(MultipartFile image, User user) throws IOException {
        var userId = user.getUserId();

        userService.saveProfileUrl(image, userId);
    }


    public UserResponse changePassword(Api<ChangePasswordRequest> req, User user) {

        var data = req.getBody();

        var userId = user.getUserId();

        var saveEntity = userService.changePassword(userId, data);

        return userConverter.toResponse(saveEntity);
    }

    public void deleteUser(User user) {

        var userEntity = userService.findByIdWithThrow(user.getUserId());

        userEntity.setStatus(UserStatus.UNREGISTER);
    }
}
