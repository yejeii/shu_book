package com.iteratrlearning.shu_book.chapter_05;

/**
 * 4차. 조건을 평가하여 분리하고, 액션을 수행시키는 인터페이스
 * 
 * ISP 위배... : InspectorTest 의 4차 메서드 참고
 */
public interface ConditionalAction {

    boolean evaluate(Facts facts);  // 조건을 평가(검사), 분리
    void perform(Facts facts);      // 액션을 수행
}
