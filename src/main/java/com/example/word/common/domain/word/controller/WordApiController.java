package com.example.word.common.domain.word.controller;

import com.example.word.common.api.Api;
import com.example.word.common.domain.word.model.WordDto;
import com.example.word.common.domain.word.model.WordInsertRequest;
import com.example.word.common.domain.word.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/word")
@RequiredArgsConstructor
public class WordApiController {

    private final WordService wordService;

    @PostMapping("/add")
    public Api<WordDto> wordInsert(
            @RequestBody
            Api<WordInsertRequest> wordInsertRequest,
            @CookieValue(name = "USER", required = false)
            String cookie
    ) {
        var wordDto = wordService.insert(wordInsertRequest.getBody(), cookie);
        return Api.OK(wordDto);
    }

    @GetMapping("/list")
    public Api<List<WordDto>> wordList(
            @CookieValue(name = "USER", required = false)
            String cookie
    ) {
        var wordDtoList = wordService.wordList(cookie);
        return Api.OK(wordDtoList);
    }

    @DeleteMapping("/delete/{wordId}")
    public Api<String> wordDelete(
            @PathVariable
            Long wordId,
            @CookieValue(name = "USER", required = false)
            String cookie
    ) {
        wordService.removeWord(wordId, cookie);


        return Api.OK("단어를 삭제했습니다.");
    }

    @PutMapping("/update/{wordId}")
    public Api<WordDto> wordUpdate(
            @PathVariable
            Long wordId,
            @RequestBody
            Api<WordInsertRequest> insertRequest,
            @CookieValue(name = "USER", required = false)
            String cookie
    ) {
        var wordDto = wordService.updateWord(wordId, insertRequest.getBody(), cookie);

        return Api.OK(wordDto);
    }




}
