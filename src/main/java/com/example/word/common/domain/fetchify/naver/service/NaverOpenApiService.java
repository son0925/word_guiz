package com.example.word.common.domain.fetchify.naver.service;

import com.example.word.common.domain.word.model.DictionaryResponse;
import com.example.word.common.domain.word.model.NaverApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NaverOpenApiService {

    @Value("${x.naver.client.id}")
    private String clientId;
    @Value("${x.naver.client.secret}")
    private String clientSecret;


    public NaverApiResponse searchDictionary(String word) {
        // 네이버 API URL
        String url = "https://openapi.naver.com/v1/search/webkr.json?query=" + word + "뜻&display="+3;

        // 헤더 설정 (Client ID와 Secret 포함)
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        // 요청 생성
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // RestTemplate로 요청 보내기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NaverApiResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                NaverApiResponse.class
        );

        return response.getBody();
    }


}
