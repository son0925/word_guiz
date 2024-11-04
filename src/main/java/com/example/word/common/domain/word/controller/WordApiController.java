package com.example.word.common.domain.word.controller;

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
    public WordDto wordInsert(
            @RequestBody
            WordInsertRequest wordInsertRequest,
            @CookieValue(name = "USER", required = false)
            String cookie
    ) {
        return wordService.insert(wordInsertRequest, cookie);
    }

    @GetMapping("/list")
    public List<WordDto> wordList(
            @CookieValue(name = "USER", required = false)
            String cookie
    ) {
        return wordService.wordList(cookie);
    }

    @DeleteMapping("/delete/{wordId}")
    public void wordDelete(
            @PathVariable
            Long wordId,
            @CookieValue(name = "USER", required = false)
            String cookie
    ) {
        wordService.removeWord(wordId, cookie);
    }

    @PutMapping("/update/{wordId}")
    public WordDto wordUpdate(
            @PathVariable
            Long wordId,
            @RequestBody
            WordInsertRequest insertRequest,
            @CookieValue(name = "USER", required = false)
            String cookie
    ) {
        return wordService.updateWord(wordId, insertRequest, cookie);
    }




}
