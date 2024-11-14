package com.example.word.common.domain.user.service;

import com.example.word.common.annotation.Converter;
import com.example.word.common.domain.user.model.UserEntity;
import com.example.word.common.domain.user.model.UserResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Converter
public class UserConverter {

    public UserEntity toEntity(UserResponse response) {
        return UserEntity.builder()
                .userId(response.getUserId())
                .password(response.getPassword())
                .name(response.getName())
                .status(response.getStatus())
                .birthdate(response.getBirthdate())
                .lastLoginTime(response.getLastLoginTime())
                .profileUrl(response.getProfileUrl())
                .build()
                ;
    }

    public UserResponse toResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .userId(userEntity.getUserId())
                .password(userEntity.getPassword())
                .name(userEntity.getName())
                .status(userEntity.getStatus())
                .birthdate(userEntity.getBirthdate())
                .lastLoginTime(userEntity.getLastLoginTime())
                .profileUrl(userEntity.getProfileUrl())
                .build()
                ;
    }

    public LocalDateTime convertToLocalDateTime(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return localDateTime;
    }
}
