package com.example.word.common.domain.user.service;

import com.example.word.common.domain.user.db.UserRepository;
import com.example.word.common.domain.user.model.ChangePasswordRequest;
import com.example.word.common.domain.user.model.UserEntity;
import com.example.word.common.domain.user.model.enums.UserStatus;
import com.example.word.common.error.ErrorCode;
import com.example.word.common.error.UserErrorCode;
import com.example.word.common.exception.ApiException;
import com.example.word.common.utils.image.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ImageService imageService;

    private final PasswordEncoder passwordEncoder;


    // 로그인
    public UserEntity login(String userId, String password) {
        var userEntity = findByIdWithThrow(userId);

        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
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

        var userEntity = UserEntity.builder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .birthdate(birthdate)
                .status(UserStatus.REGISTER)
                .build();

        return userRepository.save(userEntity);
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

        var userEntity = findByIdWithThrow(userId);

        // 비밀번호가 맞지 않을 때
        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new ApiException(UserErrorCode.LOGIN_FAILED, "회원 정보 인증 실패");
        }

        // 휴먼 계정 상태가 아닐 때
        if (userEntity.getStatus().equals(UserStatus.REGISTER)) {
            throw new ApiException(ErrorCode.BAD_REQUEST);
        }

        userEntity.setStatus(UserStatus.REGISTER);

        return userRepository.save(userEntity);
    }

    // 프로필 사진 저장하기
    public void saveProfileUrl(MultipartFile image, String userId) throws IOException {
        var path = imageService.saveImage(image);

        var userEntity = findByIdWithThrow(userId);
        userEntity.setProfileUrl(path);

        userRepository.save(userEntity);
    }

    // 비밀번호 변경하기
    public UserEntity changePassword(String userId, ChangePasswordRequest data) {

        var userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));

        var curPassword = data.getCurrentPassword();
        // 현재 비밀번호 정보를 잘 입력했는가
        if (!passwordEncoder.matches(curPassword, userEntity.getPassword())) {
            throw new ApiException(ErrorCode.BAD_REQUEST, "현재 비밀번호가 맞지 않습니다.");
        }

        var changePassword = data.getChangePassword();
        userEntity.setPassword(passwordEncoder.encode(changePassword));

        System.out.println(userEntity.getPassword());
        // 저장하기
        return userRepository.save(userEntity);
    }


    public UserEntity findByIdWithThrow(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "사용자를 찾을 수 없습니다."));
    }

    public UserEntity findByIdAndStatusWithThrow(String userId, UserStatus status) {
        return userRepository.findByUserIdAndStatus(userId, status)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "사용자를 찾을 수 없습니다."));
    }
}
