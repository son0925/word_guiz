package com.example.word.common.domain.board.controller;

import com.example.word.common.annotation.UserSession;
import com.example.word.common.api.Api;
import com.example.word.common.domain.board.business.BoardBusiness;
import com.example.word.common.domain.board.model.BoardCreateRequest;
import com.example.word.common.domain.board.model.BoardResponse;
import com.example.word.common.domain.board.model.BoardUpdateRequest;
import com.example.word.common.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardApiController {

    private final BoardBusiness boardBusiness;

    @PostMapping("/create")
    public Api<BoardResponse> createBoard(
        @UserSession
        User user,
        @RequestBody BoardCreateRequest request
    ) {
        var response = boardBusiness.createBoard(user, request);

        return Api.OK(response);
    }

    @PostMapping("/update")
    public Api<BoardResponse> updateBoard(
        @UserSession
        User user,
        @RequestBody BoardUpdateRequest request
    ) {
        var response = boardBusiness.updateBoard(user, request);

        return Api.OK(response);
    }

    @DeleteMapping("/delete/{boardId}")
    public Api<BoardResponse> delete(
            @UserSession
            User user,
            @PathVariable int boardId
    ) {
        var response = boardBusiness.deleteBoard(user, boardId);

        return Api.OK(response);
    }

    @GetMapping("/visit/{boardId}")
    public void increaseVisitCount(
            @PathVariable
            int boardId
    ) {
        boardBusiness.increaseVisitCount(boardId);
    }

}
