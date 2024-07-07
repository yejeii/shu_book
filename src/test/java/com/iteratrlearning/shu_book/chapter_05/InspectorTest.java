package com.iteratrlearning.shu_book.chapter_05;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

/**
 * Inspector : 조사관
 */
public class InspectorTest {

    @Test
    public void shouldHaveNoRulesInitially() {
        // final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();
        
        // assertEquals(0, businessRuleEngine.count());
    }
    
    @Test
    public void shouldAddTwoActions() {
        // final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();
        
        // businessRuleEngine.addAction(() -> {});
        // businessRuleEngine.addAction(() -> {});

        // assertEquals(2, businessRuleEngine.count());
    }

    /*
     * 2차. run() 검증을 위한 테스트 추가
     * 
     * Action 이 추가되면(addAction() 실행) run() 이 실행되는지에 대한 테스트
     * 
     * 근데 run() 은 결과를 반환하지 않음 -> 동작 확인을 위해 모킹이 필요
     * 
     * mock(Action.class) : Action 인터페이스를 대충 만들어주는 모조품(mock 객체) 반환
     *                      즉, Action 의 mock 객체
     *                      이를 통해 addAction() 에 Action 모조품 객체를 던져서 Action 을 add 할 수 있게 됨
     * 
     * 테스트 코드 작성 (단위 테스트)
     *  - Given-When-Then 패턴
     *    Given : 테스트를 수행하기 위한 환경 제공
     *    When : 테스트 목적 기술. 내가 구현한 메서드의 기능에 문제가 없는지 검증
     *    Then : 테스트 결과를 검증. 일반적으로 When 에서 반환된 값을 검증함
     */
    @Test
    public void shouldExecuteOneAction() {
        
        // given : 검증에 필요한 재료 준비
        // final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();
        // final Action mockAction = mock(Action.class);

        // when
        // businessRuleEngine.addAction(mockAction);   // 인터페이스이므로 mock 객체로 대충 만들어줌
        // businessRuleEngine.run();

        // then
        // verify(mockAction).perform();

    }

    /**
     * 3차. run() 검증을 위한 테스트 추가
     * 
     * 액션이 특정 조건을 만족하면 수행할 수 있도록 테스트
     * 
     * 실패를 통한 리펙토링
     * 리펙토링 1. Facts 클래스 생성
     * 리펙토링 2. Action 인터페이스 수정 : Facts 객체 인자로 받음
     *             perform() 이 Facts 객체를 사용할 수 있도록
     *          3. BusinessRuleEngine 클래스 생성자 수정 : Facts 객체 초기화
     *             규칙 엔진은 Action 을 Add 하기 위해 대상인 Facts 정보가 필요하므로.
     */
    // @Test
    // public void shouldPerformAnActionWithFacts() {
    //     final Action mockAction = mock(Action.class);
    //     final Facts mockFacts = mock(Facts.class);
    //     final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);

    //     businessRuleEngine.addAction(mockAction);
    //     businessRuleEngine.run();

    //     verify(mockAction).perform(mockFacts);

    // }

    /**
     * 4차. 대상에 대한 조건을 평가하는 테스트
     * 
     * JobTitleCondition 클래스의 ISP 위배
     *  - 우리가 원하는 것은 참 또는 거짓으로 평가할 수 있는 어떤 조건을 모델링하는 것..
     *    하지만 필요한 기능 이상을 제공하는 ConditionalAction Inf 를 구현함으로써
     *    필요없는 perfom() 에 의존하게 된 것! 
     */
    @Test
    public void inspectOneConditionEvaluatesTrue() {
        final Facts facts = new Facts();
        facts.setFact("jobTitle", "CEO");
        final ConditionalAction conditionalAction = new JobTitleCondition();
        final Inspector inspector = new Inspector(conditionalAction);

        final List<Report> reportList = inspector.inspect(facts);

        assertEquals(1, reportList.size());
        assertEquals(
            true, reportList.get(0).isPositive());
    } 

    private static class JobTitleCondition implements ConditionalAction {

        @Override
        public void perform(Facts facts) {
            throw new UnsupportedOperationException("Unimplemented method 'perform'");
        }

        @Override
        public boolean evaluate(Facts facts) {
            return "CEO".equals(facts.getFacts("jobTitle"));
        }

    }
}
