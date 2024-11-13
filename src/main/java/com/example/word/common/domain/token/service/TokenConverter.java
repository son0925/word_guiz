package com.example.word.common.domain.token.service;

import com.example.word.common.annotation.Converter;
import com.example.word.common.domain.token.model.TokenDto;
import com.example.word.common.domain.token.model.TokenResponse;
import com.example.word.common.error.ErrorCode;
import com.example.word.common.exception.ApiException;

import java.util.Objects;

@Converter
public class TokenConverter {

    public TokenResponse toResponse(TokenDto accessToken, TokenDto refreshToken) {
        Objects.requireNonNull(accessToken, () -> {throw new ApiException(ErrorCode.NULL_POINT);});
        Objects.requireNonNull(refreshToken, () -> {throw new ApiException(ErrorCode.NULL_POINT);});

        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }

}
