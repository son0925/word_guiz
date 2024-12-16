package com.example.word.common.domain.comment.controller;

import com.example.word.common.annotation.UserSession;
import com.example.word.common.api.Api;
import com.example.word.common.domain.comment.business.ReplyBusiness;
import com.example.word.common.domain.comment.model.ReplyCreateRequest;
import com.example.word.common.domain.comment.model.ReplyResponse;
import com.example.word.common.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reply")
public class ReplyApiController {

    private final ReplyBusiness replyBusiness;

    @PostMapping("/create")
    public Api<ReplyResponse> createReply(
            @RequestBody
            ReplyCreateRequest request,
            @UserSession User user
    ) {
        var response = replyBusiness.createReply(user, request);
        return Api.OK(response);
    }

    @PutMapping("/update")
    public ReplyResponse updateReply() {
        return null;
    }

    @DeleteMapping("/delete/{replyId}")
    public ReplyResponse deleteReply() {
        return null;
    }

}
