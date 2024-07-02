package com.iteratrlearning.shu_book.chapter_04;

import static com.iteratrlearning.shu_book.chapter_04.Attributes.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class InvoiceImporter implements Importer {

    @Override
    public Document importFile(File file) throws IOException {
        final TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(NAME_PREFIX, PATIENT);	// 환자명 추출
        textFile.addLineSuffix(AMOUNT_PREFIX, AMOUNT);	// 청구비용 추출

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "INVOICE");	// 현재 파싱한 문서정보 저장
        
    	// LSP 후행조건 만족
        return new Document(attributes);
    }

}
