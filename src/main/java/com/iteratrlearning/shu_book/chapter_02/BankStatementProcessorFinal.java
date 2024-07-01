package com.iteratrlearning.shu_book.chapter_02;

import java.time.Month;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * 10차
 * 기존 BankStatementProcessor 클래스 최종
 * SummaryStatistics 클래스를 리턴값으로.
 * 
 * 다양한 조건의 입출금 총액을 계산하는 책임을 진 클래스 
 */
public class BankStatementProcessorFinal {

    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessorFinal(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public SummaryStatistics summarizeTransactions() {
        final DoubleSummaryStatistics doubleSummaryStatistics = bankTransactions.stream()
                .mapToDouble(BankTransaction::getAmount)
                .summaryStatistics();
        
        return new SummaryStatistics(doubleSummaryStatistics.getSum(),
                doubleSummaryStatistics.getMax(),
                doubleSummaryStatistics.getMin(),
                doubleSummaryStatistics.getAverage());
    }

    public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
        double result = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            result = bankTransactionSummarizer.summarize(result, bankTransaction);
        }
        return result;
    }

    public double calculateTotalInMonth(final Month month) {

        return summarizeTransactions((acc, bankTransaction) -> bankTransaction.getDate().getMonth() == month ? acc + bankTransaction.getAmount() : acc);
        
        
    }

    public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter) {
        final List<BankTransaction> result = new ArrayList<>();
        for(final BankTransaction bankTransaction : bankTransactions) {
            if(bankTransactionFilter.test(bankTransaction)) {
                result.add(bankTransaction);
            }
        }
        return result;
    }


    public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
        
        return findTransactions(bankTransaction -> bankTransaction.getAmount() >= amount);

    }

    
}
