package com.example.word.common.domain.streaks.business;

import com.example.word.common.annotation.Business;
import com.example.word.common.domain.streaks.model.StreaksResponse;
import com.example.word.common.domain.streaks.service.StreaksConverter;
import com.example.word.common.domain.streaks.service.StreaksService;
import com.example.word.common.domain.user.model.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Business
@RequiredArgsConstructor
public class StreaksBusiness {

    private final StreaksService streaksService;
    private final StreaksConverter streaksConverter;


    public List<StreaksResponse> getStreaksList(User user) {
        return streaksService.getStreaksList(user.getUserId()).stream()
                .map(streaksConverter::toResponse)
                .toList()
                ;
    }

    public void plantingGrass(String userId) {
        streaksService.plantingGrass(userId);
    }
}
