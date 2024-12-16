package com.example.word.common.domain.board.service;

import com.example.word.common.domain.board.db.BoardRepository;
import com.example.word.common.domain.board.model.BoardCreateRequest;
import com.example.word.common.domain.board.model.BoardEntity;
import com.example.word.common.domain.board.model.BoardUpdateRequest;
import com.example.word.common.domain.board.model.enums.BoardStatus;
import com.example.word.common.domain.user.model.User;
import com.example.word.common.domain.user.model.UserEntity;
import com.example.word.common.domain.user.model.enums.UserRole;
import com.example.word.common.domain.user.model.enums.UserStatus;
import com.example.word.common.error.ErrorCode;
import com.example.word.common.error.UserErrorCode;
import com.example.word.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 생성하기
    public BoardEntity create(UserEntity user, BoardCreateRequest request) {

        // 사용자의 상태가 비활성화된 상태라면 에러
        if (user.getStatus().equals(UserStatus.UNREGISTER)) {
            throw new ApiException(UserErrorCode.UN_AUTHORIZATION);
        }

        // 글 작성하기
        var entity = BoardEntity.builder()
                .userId(user.getUserId())
                .title(request.getTitle())
                .content(request.getContent())
                .password(request.getPassword())
                .createAt(LocalDateTime.now())
                .visitCount(0)
                .boardLike(0)
                .status(BoardStatus.REGISTER)
                .build()
                ;

        // 관리자가 적으면 공지사항으로 변경하기
        if (user.getRole().equals(UserRole.ADMIN)) {
            entity.setStatus(BoardStatus.NOTICE);
        }

        return boardRepository.save(entity);
    }

    // 게시글 수정하기
    public BoardEntity updateBoard(User user, BoardUpdateRequest request) {
        var boardEntity = findByIdWithThrow(request.getBoardId());

        // TODO 관리자 혹은 작성자가 아니라면

        boardEntity.setTitle(request.getTitle());
        boardEntity.setContent(request.getContent());
        boardEntity.setStatus(BoardStatus.UPDATE);
        boardEntity.setPassword(request.getPassword());

        return boardRepository.save(boardEntity);
    }




    // 게시글 삭제하기
    public BoardEntity deleteBoard(User user, int boardId) {
        var boardEntity = findByIdWithThrow(boardId);

        // 작성자 혹은 관리자가 아닐 때
        isRoleAbleWithThrow(user, boardEntity.getUserId());

        boardEntity.setStatus(BoardStatus.UNREGISTER);

        return boardRepository.save(boardEntity);
    }

    // 게시글 리스트 가지고 오기(Pageable, 필터링까지)
    public Page<BoardEntity> getBoardList(Pageable pageable) {
        var sort = Sort.by(Sort.Direction.DESC, "createAt");
        var sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return boardRepository.findAllByStatusNot(BoardStatus.UNREGISTER, sortedPageable);
    }

    // 게시글 검색하기
    public List<BoardEntity> searchTitleList(String title) {
        return boardRepository.findAllByTitleContainingAndStatusNot(title, BoardStatus.UNREGISTER);
    }


    // 작성자 검색하기
    public List<BoardEntity> searchWriter(String writerId) {
        return boardRepository.findAllByUserIdAndStatusNot(writerId, BoardStatus.UNREGISTER);
    }



    public BoardEntity findByIdWithThrow(int boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new ApiException(ErrorCode.BAD_REQUEST));
    }

    private void isRoleAbleWithThrow(User user, String userId) {
        if (user.getUserId().equals(userId)) {
            return;
        }

        if (user.getRole().equals(UserRole.ADMIN)) {
            return;
        }

        throw new ApiException(UserErrorCode.UN_AUTHORIZATION);
    }


    public void increaseVisitCount(int boardId) {
        var entity = findByIdWithThrow(boardId);

        entity.setVisitCount(entity.getVisitCount() + 1);

        boardRepository.save(entity);
    }

    public BoardEntity getBoardDetail(int boardId) {
        return findByIdWithThrow(boardId);
    }
}
