package com.iteratrlearning.shu_book.chapter_04;

import java.util.Map;

/**
 * 문서관리시스템에서 관리대상이 되는 도메인 클래스
 */
class Document {

    private final Map<String, String> attributes;

    public Document(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getAttribute(final String attributeName) {
        return attributes.get(attributeName);
    }
}
