package com.example.word.common.domain.user.service;

import com.example.word.common.domain.user.db.UserEntity;
import com.example.word.common.domain.user.db.UserRepository;
import com.example.word.common.error.ErrorCode;
import com.example.word.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserEntity getUserWithThrow(String userId) {

        var userEntity = userRepository.findById(userId);

        return Optional.of(userEntity)
                .map(it -> {
                    return userEntity.get();
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));

    }
}
