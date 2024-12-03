package com.example.word.common.domain.board.controller;

import com.example.word.common.annotation.UserSession;
import com.example.word.common.api.Api;
import com.example.word.common.domain.board.business.BoardBusiness;
import com.example.word.common.domain.board.model.BoardCreateRequest;
import com.example.word.common.domain.board.model.BoardResponse;
import com.example.word.common.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardApiController {

    private final BoardBusiness boardBusiness;

    @GetMapping("/list")
    public Api<List<BoardResponse>> getBoardList(
            @PageableDefault(page = 0, size = 10)
            Pageable pageable
    ) {
        return boardBusiness.getBoardList(pageable);
    }

    @PostMapping("/create")
    public Api<BoardResponse> createBoard(
        @UserSession
        User user,
        @RequestBody BoardCreateRequest request
    ) {
        var response = boardBusiness.createBoard(user, request);

        return Api.OK(response);
    }

}
