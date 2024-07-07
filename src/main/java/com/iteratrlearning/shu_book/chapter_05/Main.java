package com.iteratrlearning.shu_book.chapter_05;

public class Main {

    public static void main(String[] args) {
        
        // Facts
        var env = new Facts();  // 지역 변수 형식 추론 -> 컴파일러가 자동으로 추론
                                // Facts env = new Facts(); : 명시적 형식 선언
        env.setFact("name", "Bob");
        env.setFact("jobTitle", "CEO");

        // BusinessRuleEngine 관리 및 실행하는 인스턴스 생성
        final var businessRuleEngine = new BusinessRuleEngine(env);

        /* 5차. Condition, Action, Rule 클래스 사용하여 Rule 생성 */
        // Rule(Condition, Action)
        // final Condition condition = (Facts facts) -> "CEO".equals(facts.getFacts("jobTitle"));
        // final Action action = (Facts facts) -> {
            // var name = facts.getFacts("name");
            // System.out.println("Relevant customer!!!: " + name);
        // };

        // final Rule rule = new DefaultRule(condition, action);

        /* 5차. Condition, Action, Rule 클래스 사용 끝 */

        /* 6차. RuleBuilder 사용하여 Rule 생성 */
        // Rule rule = new RuleBuilder()
        //                 .when(facts -> "CEO".equals(facts.getFacts("jobTitle")))
        //                 .then(facts -> {
        //                     var name = facts.getFacts("name");
        //                     System.out.println("Relevant customer!!!: " + name);
        //                 })
        //                 .createRule();
        /* 6차. RuleBuilder 사용하여 Rule 생성 끝 */
        
        /* 7차. RuleBuilder 개선하여 Rule 생성 */
        final Rule rule = RuleBuilder
                        .when(facts -> "CEO".equals(facts.getFacts("jobTitle")))
                        .then(facts -> {
                            var name = facts.getFacts("name");
                            System.out.println("Relevant customer!!!: " + name);
                        });

        // 생성된 Rule, 전용 엔진(BusinessRuleEngine)에 등록 & 사용
        businessRuleEngine.addRule(rule);
        businessRuleEngine.run();

    }
}
