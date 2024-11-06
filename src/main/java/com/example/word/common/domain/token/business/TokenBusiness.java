package com.example.word.common.domain.token.business;

import com.example.word.common.annotation.Business;
import com.example.word.common.domain.token.converter.TokenConverter;
import com.example.word.common.domain.token.model.TokenResponse;
import com.example.word.common.domain.token.service.TokenService;
import com.example.word.common.domain.user.db.UserEntity;
import com.example.word.common.error.ErrorCode;
import com.example.word.common.exception.ApiException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class TokenBusiness {

    private final TokenService tokenService;

    private final TokenConverter tokenConverter;


    public TokenResponse issueToken(UserEntity userEntity) {
        return Optional.ofNullable(userEntity)
                .map(it -> {
                    var userId = it.getUserId();

                    var accessToken = tokenService.getAccessToken(userId);
                    var refreshToken = tokenService.getRefreshToken(userId);

                    return tokenConverter.toResponse(accessToken, refreshToken);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT))
                ;
    }


    public String validationAccessToken(String token) {

        return tokenService.validationToken(token);
    }


}
