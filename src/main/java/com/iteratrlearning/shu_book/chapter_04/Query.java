package com.iteratrlearning.shu_book.chapter_04;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import static java.util.stream.Collectors.toMap;

/**
 * 1차
 * Document 검색을 위한 사전 처리 책임 클래스 
 */
class Query implements Predicate<Document>{

    private final Map<String, String> clauses;

    private Query(Map<String, String> clauses) {
        this.clauses = clauses;
    }

    /*
     * 문자열을 파싱하는 메서드
     * 
     * 검색 조건 문자열 : "patient:Joe, body:Diet Coke"
     * 
     * patient(K), Joe(V) 		-> 환자명 조회 조건
     * body(K), Diet Coke(V)	-> 본문 조회 조건
     * 
     * 1. 스트림 생성(구분자 ",")
     * 	  Arrays.stream(query.split(",")
     * 	  -> {patient:Joe} {body:Diet Coke}
     * 2. 각각의 String 배열을 중간 연산(구분자 ":")
     * 	  map(str -> str.split(":"))
     * 	  -> {patient / Joe} {body / Diet Coke}
     * 3. Map<K,V> 형태로 수집(최종 수집)
     * 	  collect(toMap(x -> x[0], x -> x[1])))
     * 	  -> <"patient", "Joe"> <"body", "Diet Coke">
     * 	
     */
    static Query parse(final String query) {
        return new Query(Arrays.stream(query.split(","))	
                            .map(str -> str.split(":"))					
                            .collect(toMap(x -> x[0], x -> x[1])));
    }

    // allMatch() : 모든 요소들이 주어진 조건에 만족하는지 여부를 검사
    //              모두 요소가 해당 조건에 모두 만족시 true 리턴
    @Override
    public boolean test(Document document) {
        return clauses.entrySet()
                    .stream()
                    .allMatch(entry -> {   
                        final String documentValue = document.getAttribute(entry.getKey());
                        final String queryValue = entry.getValue();
                        return documentValue != null && documentValue.contains(queryValue);
                    });
    }
    
}
