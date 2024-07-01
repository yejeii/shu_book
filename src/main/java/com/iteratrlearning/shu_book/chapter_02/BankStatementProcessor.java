package com.iteratrlearning.shu_book.chapter_02;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * 4차
 * 다양한 조건의 입출금 총액을 계산하는 책임을 진 클래스 
 * -> SRP 및 응집도 High
 * 
 * 
 * 9차
 * OCP 를 적용해서 정말 좋은점이 많다는 것을 알게 되었으나, 과하면 좋지 않은 점도 있음
 * 
 * 함수형 인터페이스가 너무 많이 늘어나게 되는 점이 문제가 될 수 있음
 * 	- 파악해야 할 메서드가 많아지게 되어, 복잡도 증가 : 안티 응집도 문제
 * 
 * 이러한 전체적인 딜레마 : 명시적 API 제공 vs 암묵적 API 제공
 * 
 * 단순히 입출금 내역에서 통계적 연산을 수행하는 클래스이므로 인터페이스화할 필요 X
 * 명시적 API + 묵시적 API 혼용
 * -> 가장 많이 사용되는 연산이라면 사용자가 알아보기 쉽게 명시적으로 보여지는 것 역시 필요하다고 생각
 * -> 개발자가 이해하기 쉬운 코드여야 하니까..
 * -> 자주 쓰이는 건 명시적 API 로 만들고, 그 외는 추상화시킨 묵시적 API 를 사용하게 함.
 * -> 묵시적 API : findTransactions(), summarizeTransactions() -> "자세한 기능은 얘를 써라"
 *    명시적 API : calculateTotalAmount(), findTransactionsGreaterThanEqual()
 * 
 * 팀내에서 관리기준을 새로 세워야 함 (우선순위 등을 고려하게 됨)
 */
public class BankStatementProcessor {

    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    // 9차. 묵시적 API
    public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
        double result = 0d;
        for(final BankTransaction bankTransaction : bankTransactions) {
            result = bankTransactionSummarizer.summarize(result, bankTransaction);
        }
        return result;
    }

    // 9차. 암묵적 API 를 활용해라
    // public double calculateTotalAmount() {
    //     double total = 0d;

    //     for(BankTransaction bankTransaction : bankTransactions) {
    //         total += bankTransaction.getAmount();
    //     }

    //     return total;
    // }

    // 9차. 명시적 API로
    // 개발자가 보기엔 가장 많이 사용하는 메서드 -> 사용자 친화적 API로 변경
    public double calculateTotalInMonth(final Month month) {

        // double result = 0d;
        // for(final BankTransaction bankTransaction : bankTransactions) {
            // if(bankTransaction.getDate().getMonth() == month) {
                // total += bankTransaction.getAmount();
            // }
        // }                                                    
        // return total;

        // 8차. 람다식 : 객체 덩어리 형태로 생각!
        // double result = summarizeTransactions(new BankTransactionSummarizer() {
        //     @Override
        //     public double summarize(double acc, BankTransaction bankTransaction) {
        //         return bankTransaction.getDate().getMonth() == month ? acc + bankTransaction.getAmount() : acc;
        //     }
        // });

        // return result;

        return summarizeTransactions((acc, bankTransaction) -> bankTransaction.getDate().getMonth() == month ? acc + bankTransaction.getAmount() : acc);
        
        
    }

    // public double calculateTotalForCategory(final String category) {
    //     double total = 0d;
        
    //     for (final BankTransaction bankTransaction : bankTransactions) {
    //         if (bankTransaction.getDescription().equals(category)) {
    //             total += bankTransaction.getAmount();
    //         }
    //     }
    //     return total;
    // }

    // 7차. 함수형 인터페이스 적용 -> 기능의 인터페이스화, 기능의 다형성
    // 6차에 작성한 3개의 메서드 모두 ArrayList 객체를 생성, for 문을 돌면서 배열 객체에 값을 추가하는 액션이 중복되고 있음
    // -> OCP 위베 -> 인터페이스로 생성 : test 메서드 하나만 있으면 되므로 -> 함수형 인터페이스가 됨 -> 람다식으로 이용 가능!! 
    // -> 반복 로직과 비즈니스 로직의 결합 제거
    public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter) {
        final List<BankTransaction> result = new ArrayList<>();
        for(final BankTransaction bankTransaction : bankTransactions) {
            if(bankTransactionFilter.test(bankTransaction)) {
                result.add(bankTransaction);
            }
        }
        return result;
    }


    // 6차. 입출금 내역 분석 기능 추가(기능 확장) : findTransactionsGreaterThanEqual , findTransactionsInMonth , findTransactionsInMonthAndGreater
    
    // 9차. 명시적 API로.. 
    public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
        
        // final List<BankTransaction> result = new ArrayList<>();
        // for(final BankTransaction bankTransaction : bankTransactions) {
        //     if(bankTransaction.getAmount() >= amount) {
        //         result.add(bankTransaction);
        //     }
        // }
        // return result;

        return findTransactions(bankTransaction -> bankTransaction.getAmount() >= amount);

    }

    // 9차. 암묵적 API 를 활용해라
    // public List<BankTransaction> findTransactionsInMonth(final Month month) {

        // final List<BankTransaction> result = new ArrayList<>();
        // for(final BankTransaction bankTransaction : bankTransactions) {
            //     if(bankTransaction.getDate().getMonth() == month) {
                //         result.add(bankTransaction);
                //     }
                // }
                // return result;
            // }
    // }

    // 9차. 암묵적 API 를 활용해라
    // public List<BankTransaction> findTransactionsInMonthAndGreater(final Month month, final int amount) {

        // final List<BankTransaction> result = new ArrayList<>();
        // for(final BankTransaction bankTransaction : bankTransactions) {
        //     if(bankTransaction.getDate().getMonth() == month && 
        //     bankTransaction.getAmount() >= amount) {
        //         result.add(bankTransaction);
        //     }
        // }
        // return result;
    // }

    
}
