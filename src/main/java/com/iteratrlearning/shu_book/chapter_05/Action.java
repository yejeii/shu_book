package com.iteratrlearning.shu_book.chapter_05;

// 5차. 도메인 모델링 과정에서 메서드명 변경 : perform -> execute
@FunctionalInterface
public interface Action {
    void execute(Facts facts);  // 각 Action 은 고유한 Facts 를 가진다.
}
