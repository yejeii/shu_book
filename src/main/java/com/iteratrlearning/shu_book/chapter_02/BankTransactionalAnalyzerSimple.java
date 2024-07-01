package com.iteratrlearning.shu_book.chapter_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionalAnalyzerSimple {
    private static final String RESOURCES = "src/main/resources/";

    public static void main(String[] args) throws IOException {

        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final List<String> lines = Files.readAllLines(path);

        // 1차
        // printAmount(lines);

        // 2차
        BankStatementJSONParser csvParser = new BankStatementJSONParser();
        List<BankTransaction> bankTransactions = csvParser.parseLinesFromCSV(lines);

        // 2차 결과 도출 -> 3차
        // double total = 0d;
        // for(BankTransaction bankT : bankList) {
        //     total += bankT.getAmount();
        // }

        System.out.println("The total for all transactions is " + calculateTotalAmount(bankTransactions));

        // List<BankTransaction> janList = 
        //     bankList.stream().filter(bank -> bank.getDate().getMonth() == Month.JANUARY).collect(Collectors.toList());
        // for(BankTransaction jan : janList) {
        //     System.out.println(jan.toString());
        // }

        System.out.println("The total for all transactions in January is " + calculateTotalAmount(selectInMonth(bankTransactions, Month.JANUARY)));


    }

    // 3차 -> 4차 : 클래스로 분리
    public static double calculateTotalAmount(final List<BankTransaction> bankTransactions) {
        double total = 0d;

        for(BankTransaction bankTransaction : bankTransactions) {
            total += bankTransaction.getAmount();
        }

        return total;
    }

    public static List<BankTransaction> selectInMonth(final List<BankTransaction> bankTransactions,
                                                        final Month month) {
        final List<BankTransaction> bankTransactionsInMonth = new ArrayList<>();
        for(final BankTransaction bankTransaction : bankTransactions) {
            if(bankTransaction.getDate().getMonth() == month) {
                bankTransactionsInMonth.add(bankTransaction);
            }
        }                                                    
        return bankTransactionsInMonth;
    }


    // 1차
    public static void printAmount(List<String> lines) {
        double total = 0d;

        for(final String line : lines) {
            final String[] columns = line.split(",");
            final double amount = Double.parseDouble(columns[1]);
            total += amount;
        }
        
        System.out.println("The total for all transactions is " + total);
    }

    public static void printByMonth(List<String> lines) {
        double total = 0d;
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for(final String line : lines) {
            final String[] columns = line.split(",");
            final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
            if(date.getMonth() == Month.JANUARY) {
                final double amount = Double.parseDouble(columns[1]);
                total += amount;
            }
        }
        
        System.out.println("The total for all transactions in January is " + total);
    }
}
