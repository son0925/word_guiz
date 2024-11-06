package com.example.word.common.domain.user.service;

import com.example.word.common.domain.user.db.UserEntity;
import com.example.word.common.domain.user.db.UserRepository;
import com.example.word.common.error.ErrorCode;
import com.example.word.common.error.UserErrorCode;
import com.example.word.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    // 로그인
    public UserEntity login(String userId, String password) {

        var user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new ApiException(UserErrorCode.LOGIN_FAILED, "아이디 혹은 비밀번호가 틀립니다.");
        }

        var userEntity = user.get();

        if (!userEntity.getPassword().equals(password)) {
            throw new ApiException(UserErrorCode.LOGIN_FAILED, "아이디 혹은 비밀번호가 틀립니다.");
        }

        return userEntity;
    }



    // 회원가입
    public UserEntity register(String userId, String password, String name) {

        var userEntity = UserEntity.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .build();

        return userRepository.save(userEntity);
    }


    public UserEntity getUserWithThrow(String userId) {

        var userEntity = userRepository.findById(userId);

        return Optional.of(userEntity)
                .map(it -> {
                    return userEntity.get();
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));

    }
}
