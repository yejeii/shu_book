package com.iteratrlearning.shu_book.chapter_05;

/**
 * 5차. 
 */
public class DefaultRule implements Rule {

    private final Condition condition;
    private final Action action;

    public DefaultRule(final Condition condition, final Action action) {
        this.condition = condition;
        this.action = action;
    }

    @Override
    public void perform(final Facts facts) {
        if(condition.evaluate(facts)) {
            action.execute(facts);
        }
    }

}
