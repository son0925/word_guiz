package com.example.word.common.domain.token.service;

import com.example.word.common.domain.token.ifs.TokenHelperIfs;
import com.example.word.common.domain.token.model.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenHelperIfs tokenHelperIfs;


    public TokenDto getAccessToken(String id) {
        var data = new HashMap<String, Object>();
        data.put("userId", id);

        return tokenHelperIfs.issueAccessToken(data);
    }

    public TokenDto getRefreshToken(String id) {
        var data = new HashMap<String,Object>();

        data.put("userId", id);

        return tokenHelperIfs.issueRefreshToken(data);
    }


    public String validationToken(String token) {
        var map = tokenHelperIfs.validationTokenWithThrow(token);

        var userId = map.get("userId");

        return userId.toString();
    }

}
