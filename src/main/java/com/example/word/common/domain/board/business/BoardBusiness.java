package com.example.word.common.domain.board.business;

import com.example.word.common.annotation.Business;
import com.example.word.common.api.Api;
import com.example.word.common.api.Pagination;
import com.example.word.common.domain.board.model.BoardCreateRequest;
import com.example.word.common.domain.board.model.BoardEntity;
import com.example.word.common.domain.board.model.BoardResponse;
import com.example.word.common.domain.board.model.BoardUpdateRequest;
import com.example.word.common.domain.board.service.BoardConverter;
import com.example.word.common.domain.board.service.BoardService;
import com.example.word.common.domain.user.model.User;
import com.example.word.common.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Business
@RequiredArgsConstructor
public class BoardBusiness {

    private final BoardService boardService;
    private final BoardConverter boardConverter;
    private final UserService userService;


    // 게시글 생성
    public BoardResponse createBoard(User user, BoardCreateRequest request) {
        // TODO 예외 처리하기

        var userEntity = userService.findByIdWithThrow(user.getUserId());
        var entity = boardService.create(userEntity, request);

        return boardConverter.toResponse(entity);
    }


    // 게시글 업데이트
    public BoardResponse updateBoard(User user, BoardUpdateRequest request) {
        // TODO 예외 처리하기

        var updateEntity = boardService.updateBoard(user, request);

        return boardConverter.toResponse(updateEntity);
    }


    // 게시글 삭제하기
    public BoardResponse deleteBoard(User user, int boardId) {
        var deleteEntity = boardService.deleteBoard(user, boardId);

        return boardConverter.toResponse(deleteEntity);
    }

    // 게시글 리스트
    // TODO API 여기서 호출하면 안될 듯 WORD에서도 똑같이 수정하자
    public Api<List<BoardResponse>> getBoardList(Pageable pageable) {
        var boardPage = boardService.getBoardList(pageable);

        var response = boardPage.getContent().stream()
                .map(boardConverter::toResponse)
                .toList()
                ;

        var pagination = Pagination.builder()
                .curPage(boardPage.getNumber())
                .curElement(boardPage.getNumberOfElements())
                .size(boardPage.getSize())
                .totalPage(boardPage.getTotalPages())
                .totalElement(boardPage.getTotalElements())
                .build();

        return Api.OK(response, pagination);
    }
}
