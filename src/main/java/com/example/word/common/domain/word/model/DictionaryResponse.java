package com.example.word.common.domain.word.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DictionaryResponse {

    private String title;

    private String link;

    private String description;

}
