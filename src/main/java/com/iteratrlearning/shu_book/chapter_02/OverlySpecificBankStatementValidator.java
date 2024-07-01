package com.iteratrlearning.shu_book.chapter_02;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * 10차
 * 파일의 데이터 검증 관련 예외를 처리하는 클래스
 */
public class OverlySpecificBankStatementValidator {

    private String description;
    private String date;
    private String amount;

    public OverlySpecificBankStatementValidator(String description, String date, String amount) {
        this.description = Objects.requireNonNull(description);
        this.date = Objects.requireNonNull(date);
        this.amount = Objects.requireNonNull(amount);
    }
    
    // 11치. 예외를 던지지 않고 Notification 객체에 메시지 추가
    public Notification validate() {

        final Notification notification = new Notification();
        if(this.description.length() > 100) {
            notification.addError("The description is too long");   
        }

        final LocalDate parseDate;
        try {
            parseDate = LocalDate.parse(this.date);
            if(parseDate.isAfter(LocalDate.now())) {
                notification.addError("Date cannot be in the future");  
            }
        } catch (DateTimeParseException e) {
            notification.addError("Invalid format for date");
        }

        final double amount;
        try {
            amount = Double.parseDouble(this.amount);
        } catch (NumberFormatException e) {
            notification.addError("Invalid format for amount");
        }

        return notification;
    }
}
