package com.example.word.common.domain.streaks.service;

import com.example.word.common.domain.streaks.db.StreaksRepository;
import com.example.word.common.domain.streaks.model.StreaksEntity;
import com.example.word.common.error.ErrorCode;
import com.example.word.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StreaksService {

    private final StreaksRepository streaksRepository;

    // 잔디 보여주기
    public List<StreaksEntity> getStreaksList(String userId) {
        return streaksRepository.findByUserId(userId);
    }

    public void plantingGrass(String userId) {
        var date = LocalDate.now();

        var id = StreaksEntity.StreaksId.builder()
                .userId(userId)
                .date(date)
                .build()
                ;

        if (!streaksRepository.existsById(id)) {
            var entity = StreaksEntity.builder()
                    .userId(userId)
                    .date(date)
                    .problemsSolved(1)
                    .build();
            streaksRepository.save(entity);
            return;
        }

        var entity = streaksRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.SERVER_ERROR));

        entity.setProblemsSolved(entity.getProblemsSolved() + 1);
        streaksRepository.save(entity);
    }
}
