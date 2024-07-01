package com.iteratrlearning.shu_book.chapter_02;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {
    
    /*
     * private : 헤당 변수는 여기 클래스에서만 사용할 것
     * final : 재할당 X == 초기화할 떄 이미 끝. 
     *         그러나 클래스 생성 될때마다 새로이 메모리에 올라감 => 상수인데 메모리 낭비
     * -> static 첨가하여 공유되게 함
     */
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /* 
     * 기능 : 한 줄 파싱 
     * 
     * BankTransactionalAnalyzerSimple 의 각 메서드 안의 for 문 내용을 메서드화 */
    @Override
    public BankTransaction parseFrom(String line) {
        final String[] columns = line.split(",");

        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];

        return new BankTransaction(date, amount, description);
    }

    /* 
     * 기능 : CSV 파일 파싱 
     * */
    @Override
    public List<BankTransaction> parseLinesFrom(List<String> lines) {
        final List<BankTransaction> bankTransactions = new ArrayList<>();
        for(final String line: lines) {
            bankTransactions.add(parseFrom(line));  
        }
        return bankTransactions;
    }

}
