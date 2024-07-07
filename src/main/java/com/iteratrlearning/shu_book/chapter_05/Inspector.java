package com.iteratrlearning.shu_book.chapter_05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 4차. 조건을 평가하는 책임을 지는 클래스
 */
public class Inspector {

    private final List<ConditionalAction> conditionalActionList;

    public Inspector(final ConditionalAction... conditionalActions) {
        this.conditionalActionList = Arrays.asList(conditionalActions);
    }

    /*
     * 대상을 평가한 후, 
     * 대상(Facts), 조건부 액션(Action by Condition), 결과를 포함하는 리포트 목록 반환
     */
    public List<Report> inspect(final Facts facts) {
        final List<Report> reportList = new ArrayList<>();
        for(ConditionalAction conditionalAction : conditionalActionList) {
            final boolean conditionResult = conditionalAction.evaluate(facts);
            reportList.add(new Report(facts, conditionalAction, conditionResult));
        }
        return reportList;
    }

    
}
