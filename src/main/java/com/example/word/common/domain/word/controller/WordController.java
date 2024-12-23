package com.example.word.common.domain.word.controller;

import com.example.word.common.annotation.UserSession;
import com.example.word.common.api.Api;
import com.example.word.common.domain.user.model.User;
import com.example.word.common.domain.word.business.WordBusiness;
import com.example.word.common.domain.word.model.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
            @UserSession User user,
            @RequestParam(defaultValue = "word") String sortBy,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "") String searchWord,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        return wordBusiness.getWordList(user, pageable, sortBy, order, searchWord);
    }


    @DeleteMapping("/delete/{wordId}")
    public Api<String> deleteWord(
            @PathVariable Long wordId,
            @UserSession User user
    ) {
        wordBusiness.deleteWord(wordId, user);

        return Api.OK("단어가 삭제되었습니다.");
    }

    @GetMapping("/quiz")
    public Api<List<WordResponse>> getWordQuizList(
            @UserSession User user,
            @RequestParam int size
    ) {
        return Api.OK(wordBusiness.getWordQuizList(user, size));
    }

    @PostMapping("/search-dictionary")
    public Api<List<DictionaryResponse>> getDictionaryList(
            @RequestParam String word
    ) {
        var response = wordBusiness.searchDictionary(word);


        return Api.OK(response);
    }
}
