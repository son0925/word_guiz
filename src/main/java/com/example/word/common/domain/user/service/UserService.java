package com.example.word.common.domain.user.service;

import com.example.word.common.domain.user.model.UserEntity;
import com.example.word.common.domain.user.db.UserRepository;
import com.example.word.common.domain.user.model.enums.UserStatus;
import com.example.word.common.error.ErrorCode;
import com.example.word.common.error.UserErrorCode;
import com.example.word.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

        if (userEntity.getStatus().equals(UserStatus.UNREGISTER)) {
            throw new ApiException(UserErrorCode.UNREGISTER_USER, "탈퇴한 회원입니다. 계정 활성화 페이지에서 요청하세요");
        }

        userEntity.setLastLoginTime(LocalDateTime.now());

        userRepository.save(userEntity);

        return userEntity;
    }



    // 회원가입
    public UserEntity register(String userId, String password, String name, LocalDateTime birthdate) {

        var optionalUser = userRepository.findById(userId);

        // 이미 등록된 회원일 때
        if (optionalUser.isPresent()) {
            var user = optionalUser.get();

            if (user.getStatus().equals(UserStatus.REGISTER)) {
                throw new ApiException(UserErrorCode.EXISTENT_USER, "이미 등록된 회원입니다.");
            }

            throw new ApiException(UserErrorCode.UNREGISTER_USER, "탈퇴한 회원입니다.");
        }

        // TODO 비밀번호 암호화
        var userEntity = UserEntity.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .birthdate(birthdate)
                .status(UserStatus.REGISTER)
                .build();

        return userRepository.save(userEntity);
    }


    public UserEntity getUserWithThrow(String userId) {

        var userEntity = userRepository.findByUserIdAndStatus(userId, UserStatus.REGISTER);

        return Optional.of(userEntity)
                .map(it -> {
                    return userEntity.get();
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));

    }

    // 아이디 중복 여부 확인하기
    public String existentUserWithThrow(String userId) {
        if (userRepository.existsById(userId)) {
            throw new ApiException(UserErrorCode.EXISTENT_USER);
        }
        return "사용가능한 아이디입니다.";
    }


    // 휴먼 계정 활성화하기
    public UserEntity accountActivation(String userId, String password) {
        var optionalUserEntity = userRepository.findById(userId);

        if (optionalUserEntity.isEmpty()) {
            throw new ApiException(ErrorCode.NULL_POINT);
        }

        var userEntity = optionalUserEntity.get();

        // TODO 암호화로 변경 시 변경해야함
        if (!userEntity.getPassword().equals(password)) {
            throw new ApiException(UserErrorCode.LOGIN_FAILED, "회원 정보 인증 실패");
        }

        if (userEntity.getStatus().equals(UserStatus.REGISTER)) {
            throw new ApiException(ErrorCode.BAD_REQUEST);
        }

        userEntity.setStatus(UserStatus.REGISTER);

        return userRepository.save(userEntity);
    }
}
