package com.example.word.common.domain.user.service;

import com.example.word.common.domain.user.db.UserEntity;
import com.example.word.common.domain.user.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    UserEntity toEntity(UserDto userDto) {
        return UserEntity.builder()
                .userId(userDto.getUserId())
                .password(userDto.getPassword())
                .name(userDto.getName())
                .build()
                ;
    }

    UserDto toDto(UserEntity userEntity) {
        return UserDto.builder()
                .userId(userEntity.getUserId())
                .password(userEntity.getPassword())
                .name(userEntity.getName())
                .build()
                ;
    }
}
