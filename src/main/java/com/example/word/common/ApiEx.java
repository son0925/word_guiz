package com.example.word.common;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiEx<T> {

    private T body;

    private PaginationEx pagination;



}
