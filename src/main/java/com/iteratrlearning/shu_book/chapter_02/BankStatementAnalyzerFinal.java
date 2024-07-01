package com.iteratrlearning.shu_book.chapter_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * 10차
 * 기존 BankStatementAnalyzer 클래스 최종
 * 
 * 입출금 내역 보고서를 작성하는 책임
 */
public class BankStatementAnalyzerFinal {

    private static final String RESOURCES = "src/main/resources/";
    
    // interface 를 활용한 약한 결합!!! 
    public void analyze(final String fileName
                        , final BankStatementParser bankStatementParser
                        , final Exporter exporter) throws IOException{

        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);
        
        // 파싱 책임을 위임
        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
        
        // 보고서 작성에 필요한 통계처리
        final BankStatementProcessorFinal bankStatementProcessor = new BankStatementProcessorFinal(bankTransactions);
        final SummaryStatistics summaryStatistics = bankStatementProcessor.summarizeTransactions();

        // 다양한 형식으로 내보내기
        System.out.println(exporter.export(summaryStatistics));
    }


}
