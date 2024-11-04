package com.example.word.common.domain.word.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WordDto {

    private Long wordId;

    private String word;

    private String mean;

    private String userId;

}
