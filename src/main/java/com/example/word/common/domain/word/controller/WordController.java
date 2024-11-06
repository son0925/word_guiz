package com.example.word.common.domain.word.controller;

import com.example.word.common.annotation.UserSession;
import com.example.word.common.api.Api;
import com.example.word.common.domain.user.model.User;
import com.example.word.common.domain.word.business.WordBusiness;
import com.example.word.common.domain.word.model.WordRequest;
import com.example.word.common.domain.word.model.WordResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/word")
@RequiredArgsConstructor
public class WordController {

    private final WordBusiness wordBusiness;

    @PostMapping("/add")
    public Api<WordResponse> wordSave(
            @Valid
            @RequestBody
            Api<WordRequest> wordRequest,
            @UserSession User user
    ) {
        return Api.OK(wordBusiness.saveWord(wordRequest, user));
    }
}
