package com.iteratrlearning.shu_book.chapter_04;

import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;

/**
 * 함수형 인터페이스를 사용하는 3가지 방법
 *
 * 1. 객체 생성
 *  - 부가적인 기능을 Predicate 구현체에 덧부텨 추가적인 액션을 취해야 할 때 유용
 * 2. 람다 표현식
 * 3. 메서드 참조 
 */
public class PredicateTest {

    public static List<String> names = List.of("Adam", "Aaron", "Elon", "David", "Jane");

    @Test
    public void predicateFunctionalInterfaceImpl() {
        
        /*
         * 1. 객체 생성
         * 
         * 람다식을 이용해 추상 메서드(test())만 추가적으로 구현한 Predicate<> 객체를 생성한다.
         */
        Predicate<String> myPredicate = name -> name.startsWith("A");

        names.stream()
                .filter(myPredicate)   // -> 생성한 myPredicate 인스턴스를 던짐 -> 자동으로 오버라이딩된 test() 가 쓰여짐
                // .filter(name -> name.startsWith("A"))
                .forEach(System.out::println);
    }

    /**
     * 3. 메서드 참조
     * 
     * 그동안 우린 람다식을 사용하여 이름없는 메소드를 만들었다. 
     * 그러나 가끔은 람다식이 기존 메소드를 호출하는 것 외에 아무것도 하지 않을 때가 있다. 
     * 이런 경우, 기존 메소드 이름 자체를 참조하는 것이 좀 더 명확한 경우가 있다. 
     * '메소드 참조'는 이걸 가능하게 해준다. 
     * 
     * 즉, 메서드 참조란, 이미 이름이 있는 메소드에 대한 간결하고, 읽기 쉬운 람다식이다.
     * 메소드 참조는 결국 람다식을 한층 더 간결하게 표현해준다는 것.
     */
    @Test
    public void methodReference() {
        // startsWithAlpha(String::isEmpty);       // 아래와 문법만 다를뿐, 같은 람다식 표현!! -> 이름없이 구현한 객체로 던져지게 된다 :)
        startsWithAlpha(s -> s.isEmpty());   
    }

    public void startsWithAlpha(Predicate<String> withA) {
        for(String name: names) {
            if(withA.test(name)) {
                System.out.println(name);
            }
        }
    }
}
