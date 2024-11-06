package com.example.word.common.domain.word.controller;

import com.example.word.common.annotation.UserSession;
import com.example.word.common.api.Api;
import com.example.word.common.domain.user.model.User;
import com.example.word.common.domain.word.business.WordBusiness;
import com.example.word.common.domain.word.model.WordSaveRequest;
import com.example.word.common.domain.word.model.WordResponse;
import com.example.word.common.domain.word.model.WordUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/word")
@RequiredArgsConstructor
public class WordController {

    private final WordBusiness wordBusiness;

    @PostMapping("/add")
    public Api<WordResponse> wordSave(
            @Valid
            @RequestBody
            Api<WordSaveRequest> wordRequest,
            @UserSession User user
    ) {
        return Api.OK(wordBusiness.saveWord(wordRequest, user));
    }

    @PutMapping("/update")
    public Api<WordResponse> wordUpdate(
            @Valid
            @RequestBody
            Api<WordUpdateRequest> wordRequest,
            @UserSession User user
    ) {
        return Api.OK(wordBusiness.updateWord(wordRequest, user));
    }
}
