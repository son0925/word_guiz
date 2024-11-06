package com.example.word.common.domain.user.service;

import com.example.word.common.annotation.Converter;
import com.example.word.common.domain.user.db.UserEntity;
import com.example.word.common.domain.user.model.UserResponse;

@Converter
public class UserConverter {

    UserEntity toEntity(UserResponse userDto) {
        return UserEntity.builder()
                .userId(userDto.getUserId())
                .password(userDto.getPassword())
                .name(userDto.getName())
                .build()
                ;
    }

    UserResponse toResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .userId(userEntity.getUserId())
                .password(userEntity.getPassword())
                .name(userEntity.getName())
                .build()
                ;
    }
}
