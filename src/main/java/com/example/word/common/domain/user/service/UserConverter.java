package com.example.word.common.domain.user.service;

import com.example.word.common.annotation.Converter;
import com.example.word.common.domain.user.db.UserEntity;
import com.example.word.common.domain.user.model.UserResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Converter
public class UserConverter {

    public UserEntity toEntity(UserResponse userDto) {
        return UserEntity.builder()
                .userId(userDto.getUserId())
                .password(userDto.getPassword())
                .name(userDto.getName())
                .birthdate(userDto.getBirthdate())
                .lastLoginTime(userDto.getLastLoginTime())
                .profileUrl(userDto.getProfileUrl())
                .build()
                ;
    }

    public UserResponse toResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .userId(userEntity.getUserId())
                .password(userEntity.getPassword())
                .name(userEntity.getName())
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
