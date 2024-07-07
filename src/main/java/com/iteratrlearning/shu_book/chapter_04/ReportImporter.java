package com.iteratrlearning.shu_book.chapter_04;

import static com.iteratrlearning.shu_book.chapter_04.Attributes.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ReportImporter implements Importer {

    private static final String NAME_PREFIX = "Patient: ";

    @Override
    public Document importFile(File file) throws IOException {
        final TextFile textFile = new TextFile(file);
        textFile.addLineSuffix(NAME_PREFIX, PATIENT);
        textFile.addLines(2, line -> false, BODY);  // 람다식 : 항상 false 반환

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "REPORT");
        return new Document(attributes);
    }

}
