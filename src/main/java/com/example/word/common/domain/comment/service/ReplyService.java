package com.example.word.common.domain.comment.service;

import com.example.word.common.domain.comment.db.ReplyRepository;
import com.example.word.common.domain.comment.model.ReplyCreateRequest;
import com.example.word.common.domain.comment.model.ReplyEntity;
import com.example.word.common.domain.comment.model.enums.ReplyStatus;
import com.example.word.common.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    public List<ReplyEntity> getCommentList(int boardId) {
        return replyRepository.findAllByBoardId(boardId);
    }

    public ReplyEntity createReply(User user, ReplyCreateRequest request) {
        var entity = ReplyEntity.builder()
                .userId(user.getUserId())
                .boardId(request.getBoardId())
                .comment(request.getComment())
                .createAt(LocalDateTime.now())
                .status(ReplyStatus.REGISTER)
                .build()
                ;

        return replyRepository.save(entity);
    }
}
