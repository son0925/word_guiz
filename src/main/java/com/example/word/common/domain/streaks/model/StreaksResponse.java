package com.example.word.common.domain.streaks.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StreaksResponse {

    private String userId;

    private LocalDate date;

    private Integer problemsSolved;
    

}
