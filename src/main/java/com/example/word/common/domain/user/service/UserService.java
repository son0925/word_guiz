package com.example.word.common.domain.user.service;

import com.example.word.common.api.Api;
import com.example.word.common.api.Result;
import com.example.word.common.domain.user.db.UserRepository;
import com.example.word.common.domain.user.model.LoginRequest;
import com.example.word.common.domain.user.model.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;

    private final UserConverter userConverter;


    // 회원가입
    public UserDto register(UserDto userDto) {

        // 회원가입 로직
        // 1. 아이디 비밀번호 있는지 확인
        var id = userDto.getUserId();
        var password = userDto.getPassword();
        var name = userDto.getName();

        if (id == null || password == null || name == null) {
            throw new RuntimeException("빈 칸이 있습니다. 다시 시도해주세요");
        }

        var userEntity = userConverter.toEntity(userDto);

        userRepository.save(userEntity);

        return userConverter.toDto(userEntity);
    }


    public UserDto login(LoginRequest loginRequest, HttpServletResponse httpServletResponse) {

        var id = loginRequest.getUserId();
        var password = loginRequest.getPassword();

        var optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("아이디 혹은 비밀번호가 맞지 않습니다.");
        }

        var user = optionalUser.get();

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("아이디 혹은 비밀번호가 맞지 않습니다.");
        }


        Cookie cookie = new Cookie("USER", user.getUserId());
        cookie.setDomain("localhost");  // 쿠키의 도메인 설정 (localhost)
        cookie.setPath("/"); // 모든 경로에서 쿠키 사용 가능
        cookie.setHttpOnly(true); // JavaScript에서 접근 불가, 보안 강화
        cookie.setMaxAge(-1);  // 브라우저 종료 시 쿠키 삭제
        cookie.setSecure(false); // HTTP에서도 쿠키 사용 가능

        httpServletResponse.addCookie(cookie);

        return userConverter.toDto((user));

    }

    public void logout(HttpServletResponse httpServletResponse) {

        Cookie cookie = new Cookie("USER", null);
        cookie.setMaxAge(0); // 쿠키 만료
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);

    }
}
