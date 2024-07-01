package com.iteratrlearning.shu_book.chapter_04;

import static java.util.stream.Collectors.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

public class DocumentManagementSystemTest {

    private static final String RESOURCES =
        "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    private static final String LETTER = RESOURCES + "patient.letter";
    private static final String REPORT = RESOURCES + "patient.report";
    private static final String XRAY = RESOURCES + "xray.jpg";
    private static final String INVOICE = RESOURCES + "patient.invoice";
    private static final String JOE_BLOGGS = "Joe Bloggs";


    // 검색 조건 문자열 : "patient:Joe, body:Diet Coke"
    @Test
    public void searchByQuery() {
        final String str = "patient:Joe, body:Diet Coke";
        String[] splitByComma = str.split(",");

        System.out.println("splitByComma 요소 확인");
        System.out.println(splitByComma[0]);
        System.out.println(splitByComma[1]);
        
        System.out.println();
        
        List<String[]> splitByColonList = Arrays.stream(splitByComma).map(s -> s.split(":")).collect(Collectors.toList());
        System.out.println("splitByColonList 요소 확인");
        splitByColonList.stream().forEach(s -> System.out.println(String.valueOf(s)));
        
        System.out.println();
        
        Map<String, String> clauses = Arrays.stream(splitByComma)
                                            .map(s -> s.split(":"))
                                            .collect(toMap(x -> x[0], x -> x[1]));
        System.out.println("clauses 요소 확인");
        clauses.forEach((key, val) -> {
            System.out.println(key + ": " + val);
        });
        

    }

}
