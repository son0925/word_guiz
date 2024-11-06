package com.example.word.common.domain.word.controller;

import com.example.word.common.annotation.UserSession;
import com.example.word.common.api.Api;
import com.example.word.common.domain.user.model.User;
import com.example.word.common.domain.word.business.WordBusiness;
import com.example.word.common.domain.word.model.WordDeleteRequest;
import com.example.word.common.domain.word.model.WordSaveRequest;
import com.example.word.common.domain.word.model.WordResponse;
import com.example.word.common.domain.word.model.WordUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/list")
    public Api<List<WordResponse>> getWordList(
            @UserSession User user
    ) {
        var wordList = wordBusiness.getWordList(user);

        return Api.OK(wordList);
    }

    @DeleteMapping("/delete")
    public Api<String> deleteWord(
            @Valid
            @RequestBody Api<WordDeleteRequest> word,
            @UserSession User user
    ) {
        System.out.println(user);
        wordBusiness.deleteWord(word, user);

        return Api.OK("단어가 삭제되었습니다.");
    }
}
