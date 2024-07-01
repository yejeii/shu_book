package com.iteratrlearning.shu_book.chapter_02;

/**
 * 7차. 기능의 인터페이스화 : 비즈니스 로직 담당
 */
@FunctionalInterface
public interface BankTransactionFilter {
    boolean test(BankTransaction bankTransaction);
}
