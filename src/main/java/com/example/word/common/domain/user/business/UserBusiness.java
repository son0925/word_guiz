package com.example.word.common.domain.user.business;

import com.example.word.common.annotation.Business;
import com.example.word.common.api.Api;
import com.example.word.common.domain.token.business.TokenBusiness;
import com.example.word.common.domain.token.model.TokenResponse;
import com.example.word.common.domain.user.model.LoginRequest;
import com.example.word.common.domain.user.model.User;
import com.example.word.common.domain.user.model.UserRegisterRequest;
import com.example.word.common.domain.user.model.UserResponse;
import com.example.word.common.domain.user.service.UserConverter;
import com.example.word.common.domain.user.service.UserService;
import com.example.word.common.error.ErrorCode;
import com.example.word.common.exception.ApiException;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;

    private final UserConverter userConverter;

    private final TokenBusiness tokenBusiness;


    public TokenResponse login(Api<LoginRequest> req) {

        var user = req.getBody();

        if (Objects.isNull(user) || user.getUserId() == null || user.getPassword() == null) {
            throw new ApiException(ErrorCode.NULL_POINT);
        }

        var userId = user.getUserId();
        var password = user.getPassword();


        var userEntity = userService.login(userId, password);

        return tokenBusiness.issueToken(userEntity);
    }

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
}
