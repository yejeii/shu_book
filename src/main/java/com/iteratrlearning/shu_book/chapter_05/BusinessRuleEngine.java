package com.iteratrlearning.shu_book.chapter_05;

import java.util.ArrayList;
import java.util.List;

// 7차. 액션 대신 규칙 지원하도록 리펙토링
public class BusinessRuleEngine {

    // private final List<Action> actions;
    private final List<Rule> rules;
    private final Facts facts;

    public BusinessRuleEngine(Facts facts) {
        this.facts = facts;
        this.rules = new ArrayList<>();
    }

    public void addRule(final Rule rule) {
        this.rules.add(rule);
    }
    
    // public int count() {
    //     return this.rules.size();
    // }
    
    // Action 이 추가될때마다(addAction()) 실행되어야 하는 책임
    public void run() {
        // this.actions.forEach(Action::perform);
        this.rules.forEach(rule -> rule.perform(facts));
    }
}
