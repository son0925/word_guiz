package com.example.word.common.domain.word.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WordResponse {

    private Long wordId;

    private String word;

    private String mean;

    private String userId;

}
