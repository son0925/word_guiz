package com.example.word.common.domain.statistics.model;

import com.example.word.common.domain.statistics.model.enums.StatisticsStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticsUpdateRequest {

    private Long wordId;

    private StatisticsStatus status;

}
