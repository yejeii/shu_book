package com.iteratrlearning.shu_book.chapter_02;

// 9차. API 를 만들기 위한 함수형 인터페이스
@FunctionalInterface
public interface BankTransactionSummarizer {

    double summarize(double accumulator, BankTransaction bankTransaction);

}
