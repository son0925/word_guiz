package com.example.word.common.domain.token.ifs;


public interface TokenHelperIfs {

    String createAccessToken(String userId);

    String createRefreshToken(String userId);

    boolean validateToken(String token);

}
