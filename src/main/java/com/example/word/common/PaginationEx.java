package com.example.word.common;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationEx {

    // 현재 페이지
    private Integer page;

    // 크기
    private Integer size;

    // 현재 가지고 있는 요소의 개수
    private Integer currentElement;

    // 총 페이지
    private Integer totalPage;

    // 총 요소 개수
    private Long totalElement;

}
