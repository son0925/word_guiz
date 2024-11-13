package com.example.word.common.domain.token.service;

import com.example.word.common.domain.token.helper.JwtTokenHelper;
import com.example.word.common.domain.token.ifs.TokenHelperIfs;
import com.example.word.common.domain.token.model.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtTokenHelper jwtTokenHelper;


    public TokenDto getAccessToken(String id) {
        var data = new HashMap<String, Object>();
        data.put("userId", id);

        return jwtTokenHelper.issueAccessToken(data);
    }

    public TokenDto getRefreshToken(String id) {
        var data = new HashMap<String,Object>();
        data.put("userId", id);

        return jwtTokenHelper.issueRefreshToken(data);
    }


    public String validationToken(String token) {
        var map = jwtTokenHelper.validationTokenWithThrow(token);

        var userId = map.get("userId");

        return userId.toString();
    }

}
