package com.iteratrlearning.shu_book.chapter_04;

import static com.iteratrlearning.shu_book.chapter_04.Attributes.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class LetterImporter implements Importer {

    private static final String NAME_PREFIX = "Dear ";

    @Override
    public Document importFile(File file) throws IOException {
        final TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(NAME_PREFIX, PATIENT);

        final int lineNumber = textFile.addLines(2, String::isEmpty, ADDRESS);
        // final int lineNumber = textFile.addLines(2, s -> s.isEmpty(), ADDRESS);
        textFile.addLines(lineNumber + 1, (line) -> line.startsWith("regards,"), BODY);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "LETTER");
        
        // LSP 후행조건 만족
        return new Document(attributes);
    }

}
