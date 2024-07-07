package com.iteratrlearning.shu_book.chapter_05;

// 5차. 조건을 검사하는 책임만 지는 도메인 모델
//      어떤 대상(Facts)에 적용할 조건(참/거짓으로 평가됨)
public interface Condition {
    boolean evaluate(Facts facts);  // 조건을 평가(검사), 분리
}
