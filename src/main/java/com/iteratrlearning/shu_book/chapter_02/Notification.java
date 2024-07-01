package com.iteratrlearning.shu_book.chapter_02;

import java.util.ArrayList;
import java.util.List;

/**
 * 11차
 * 노티피케이션 패턴 : 너무 많은 미확인 예외(Throwable, RuntimeException)를 사용하는 상황에 적합한 해결책
 * 
 * 오류를 수집하는 도메인 클래스
 */
public class Notification {
    private final List<String> errors = new ArrayList<>();

    public void addError (final String message) {
        errors.add(message);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public String errorMessage() {
        return errors.toString();
    }

    public List<String> getErrors() {
        return errors;
    }

}
