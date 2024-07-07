package com.iteratrlearning.shu_book.chapter_05;

import java.util.HashMap;
import java.util.Map;

/**
 * 3차. 액션을 유발할 대상을 저장하는 도메인 클래스
 */ 
public class Facts {

    private final Map<String, String> facts = new HashMap<>();

    public String getFacts(final String name) {
        return this.facts.get(name);
    }

    // 새로운 대상 저장하는 책임
    public void setFact(String name, String value) {
        this.facts.put(name, value);
    }

}
