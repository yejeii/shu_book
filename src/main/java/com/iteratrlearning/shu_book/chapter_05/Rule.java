package com.iteratrlearning.shu_book.chapter_05;

// 5차. 조건(Condition)과 액션(Action)을 합친 도메인 모델
//      조건이 참일 때만 액션이 실행됨
@FunctionalInterface
public interface Rule {
    void perform(Facts facts);      
}
