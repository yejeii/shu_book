package com.iteratrlearning.shu_book.chapter_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

/**
 * 5차
 * In-Put 기능의 Main 클래스로 새로 생성
 * 
 */
public class BankStatementAnalyzer {

    private static final String RESOURCES = "src/main/resources/";
    
    // 6차. 인터페이스를 통한 결합도 낮추기
    // private static final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();
    // private static final BankStatementJSONParser bankStatementParser = new BankStatementJSONParser();

    public void analyze(final String fileName, final BankStatementParser bankStatementParser) throws IOException{
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);
        
        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        // 7차. BankTransactionIsInFebruaryAndExpensive 클래스 적용
        //      -> 새로운 요구사항이 있을 떄마다 별도의 클래스 생성해야하는 어려움 존재.
        // final List<BankTransaction> transactions = bankStatementProcessor.findTransactions(new BankTransactionIsInFebruaryAndExpensive());

        // 8차. 위의 어려움을 람다 표현식으로 해결
        //      -> 람다 표현식으로 바디 구현.
        //      -> 익명 클래스로 구현할 수 있지만, 람다 표현식을 통해 이름 없이 인터페이스 객체를 코드 블럭 형태로 전달 가능해짐.
        //      -> 이름 없는 인터페이스 객체를 findTransactions 메서드의 인자로 전달
        //      -> 이름 없는 인터페이스 객체의 test() 메서드( -> 다음의 바디 내용)가 사용됨
        final List<BankTransaction> transactions = bankStatementProcessor.findTransactions(bankTransaction -> 
                                        bankTransaction.getDate().getMonth() == Month.FEBRUARY
                                                && bankTransaction.getAmount() >= 1_000);

        // final List<BankTransaction> transactions = bankStatementProcessor.findTransactions(new BankTransactionFilter() {
        //         @Override
        //         public boolean test(BankTransaction bankTransaction) {
        //                 return bankTransaction.getDate().getMonth() == Month.FEBRUARY
        //                         && bankTransaction.getAmount() >= 1_000;
        //         }
        // });

        collectSummary(bankStatementProcessor);

    }

    private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {
        // System.out.println("The total for all transactions is "
                // + bankStatementProcessor.calculateTotalAmount());
        System.out.println("The total for transactions in January is "
                + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("The total for transactions in February is "
                + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));
        // System.out.println("The total salary received is "
                // + bankStatementProcessor.calculateTotalForCategory("Salary"));
    }

}
