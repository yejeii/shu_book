package com.iteratrlearning.shu_book.chapter_04;

import static com.iteratrlearning.shu_book.chapter_04.Attributes.*;
import static java.util.stream.Collectors.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
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

    private DocumentManagementSystem system = new DocumentManagementSystem();

    @Test
    public void shouldImportFile() throws IOException {
        system.importFile(LETTER);
        final Document document = onlyDocument();
        assertAttributeEquals(document, Attributes.PATH, LETTER);
        
    }

    private void assertAttributeEquals(final Document document,
                                        final String attributeName,
                                        final String expectedValue) {
        assertEquals("Document has the wrong value for " + attributeName, 
        expectedValue,
        document.getAttribute(attributeName));
    }

    @Test
    public void shouldImportLetterAttributes() throws IOException {
        system.importFile(LETTER);
        final Document document = onlyDocument();
        assertAttributeEquals(document, PATIENT, JOE_BLOGGS);
        assertAttributeEquals(document, ADDRESS, "123 Fake Street\n" +
                "Westminster\n" +
                "London\n" +
                "United Kingdom");
        assertAttributeEquals(document, BODY,
                "We are writing to you to confirm the re-scheduling of your appointment\n" +
                "with Dr. Avaj from 29th December 2016 to 5th January 2017.");
            assertTypeIs("LETTER", document);
    }

    private void assertTypeIs(final String type, final Document document) {
        assertAttributeEquals(document, TYPE, type);
    }

    private Document onlyDocument() {
        final List<Document> documents = system.contents();
        assertThat(documents, hasSize(1));
        return documents.get(0);
    }



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
