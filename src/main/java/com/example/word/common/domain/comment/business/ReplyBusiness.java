package com.example.word.common.domain.comment.business;

import com.example.word.common.annotation.Business;
import com.example.word.common.domain.comment.model.ReplyCreateRequest;
import com.example.word.common.domain.comment.model.ReplyResponse;
import com.example.word.common.domain.comment.service.ReplyConverter;
import com.example.word.common.domain.comment.service.ReplyService;
import com.example.word.common.domain.user.model.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Business
@RequiredArgsConstructor
public class ReplyBusiness {

    private final ReplyService replyService;

    private final ReplyConverter replyConverter;


    // 해당 게시글의 댓글 리스트 가지고 오기
    public List<ReplyResponse> getCommentList(int boardId) {

        return replyService.getCommentList(boardId).stream()
                .map(replyConverter::toResponse)
                .toList()
                ;

    }

    public ReplyResponse createReply(User user, ReplyCreateRequest request) {
        // TODO NULL 처리하기
        var entity = replyService.createReply(user, request);

        return replyConverter.toResponse(entity);
    }
}
