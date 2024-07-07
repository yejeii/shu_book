package com.iteratrlearning.shu_book.chapter_05;

/**
 * 4차. 대상을 평가한 내역(대상, 조건부액션, 결과)를 저장하는 도메인 클래스 
 */
public class Report {

    private final ConditionalAction conditionalAction;
    private final Facts facts;
    private final boolean isPositive;

    public Report(Facts facts, ConditionalAction conditionalAction, boolean isPositive) {
        this.facts = facts;
        this.conditionalAction = conditionalAction;
        this.isPositive = isPositive;
    }

    public ConditionalAction getConditionalAction() {
        return conditionalAction;
    }

    public Facts getFacts() {
        return facts;
    }

    public boolean isPositive() {
        return isPositive;
    }

    @Override
    public String toString() {
        return "Report [conditionalAction=" + conditionalAction + ", facts=" + facts + ", result=" + isPositive
                + "]";
    }

    

}
