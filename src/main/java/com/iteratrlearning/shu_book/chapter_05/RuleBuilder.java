package com.iteratrlearning.shu_book.chapter_05;

/** 6차. RuleBuiler 생성
 * 
 * 5차 코드를 사용하기 위해선 개발자가 객체를 인스턴스화한 다음, 한데로 모아야 하는 번거로움이 발생...
 * -> Builder 패턴으로 Rule 객체와 필요한 조건, 액션을 만드는 과정을 개선
 */
public class RuleBuilder {

    private Condition condition;
    // private Action action;

    // 7차. RuleBuilder 개선
    private RuleBuilder(Condition condition) {
        this.condition = condition;
    }

    public static RuleBuilder when(final Condition condition) {
        // this.condition = condition;
        // return this;
        return new RuleBuilder(condition);
    }

    public Rule then(final Action action) {
        // this.action = action;
        // return this;
        return new DefaultRule(condition, action);
    }

    // public Rule createRule() {
        // return new DefaultRule(condition, action);
    // }
}
