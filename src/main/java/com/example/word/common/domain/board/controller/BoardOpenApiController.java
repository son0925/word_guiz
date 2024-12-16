package com.example.word.common.domain.board.controller;

import com.example.word.common.api.Api;
import com.example.word.common.domain.board.business.BoardBusiness;
import com.example.word.common.domain.board.model.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/open-api/board")
@RequiredArgsConstructor
public class BoardOpenApiController {

    private final BoardBusiness boardBusiness;


    @GetMapping("/list")
    public Api<List<BoardResponse>> getBoardList(
            @PageableDefault(page = 0, size = 10)
            Pageable pageable,
            @RequestParam(required = false) String writerId,
            @RequestParam(required = false) String title
    ) {
        return boardBusiness.getBoardList(pageable, writerId, title);
    }

    @GetMapping("/detail/{boardId}")
    public Api<BoardResponse> boardDetail(
            @PathVariable int boardId
    ) {
        var response = boardBusiness.getBoardDetail(boardId);

        return Api.OK(response);
    }

}
