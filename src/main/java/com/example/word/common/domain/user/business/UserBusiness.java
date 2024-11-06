package com.example.word.common.domain.user.business;

import com.example.word.common.annotation.Business;
import com.example.word.common.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;




}
