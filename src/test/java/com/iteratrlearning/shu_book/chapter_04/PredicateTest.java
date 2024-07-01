package com.iteratrlearning.shu_book.chapter_04;

import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;

public class PredicateTest {

    @Test
    public void predicateFunctionalInterface() {
        List<String> names = List.of("Adam", "Aaron", "Elon", "David", "Jane");
        
        // 람다식을 이용해 추상 메서드(test()) 만 추가적으로 구현한 이름 없는 Predicate<> 객체를 생성 
        Predicate<String> myPredicate = name -> name.startsWith("A");

        names.stream()
                .filter(myPredicate)   // -> 생성한 myPredicate 인스턴스를 던짐 -> 자동으로 오버라이딩된 test() 가 쓰여짐
                // .filter(name -> name.startsWith("A"))
                .forEach(System.out::println);
    }
}
