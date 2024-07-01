package com.iteratrlearning.shu_book.chapter_02;

import java.time.Month;

/**
 * 7차. 2월, 1,000 이상만 TRUE 를 반환
 */
public class BankTransactionIsInFebruaryAndExpensive implements BankTransactionFilter {

    @Override
    public boolean test(BankTransaction bankTransaction) {
        return bankTransaction.getDate().getMonth() == Month.FEBRUARY
            && bankTransaction.getAmount() >= 1_000;
    }

}