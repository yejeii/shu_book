package com.iteratrlearning.shu_book.chapter_04;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.unmodifiableList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DocumentManagementSystem {

    private final List<Document> documents = new ArrayList<>();
    private final List<Document> documentsView = unmodifiableList(documents);
    private final Map<String, Importer> extensionToImporter = new HashMap<>();

    public DocumentManagementSystem() {
        // extensionToImporter.put("letter", new LetterImporter());
        // extensionToImporter.put("report", new ReportImporter());
        extensionToImporter.put("jpg", new ImageImporter());
    }
    
    // 2차. LSP 선행 조건
    //      Import 하기 전 검증 수행
    void importFile(String path) throws IOException, UnknownFileTypeException {
        final File file = new File(path);
        if(!file.exists()) throw new FileNotFoundException(path);

        final int separatorIndex = path.lastIndexOf('.');
        if (separatorIndex != -1) {
            if (separatorIndex == path.length()) {
                throw new UnknownFileTypeException("No extension found For file: " + path);
            }
            final String extension = path.substring(separatorIndex + 1);

            final Importer importer = extensionToImporter.get(extension);
            if(importer == null) throw new UnknownFileTypeException("For file : " + path);

            final Document document = importer.importFile(file);
            documents.add(document);
        } else {
            throw new UnknownFileTypeException("No extension found for File : " + path);
        }
    }

    List<Document> contents() {
        return documentsView;
    }

    // 1차. 문서 관리 시스템에 등록된 문서 검색
    public List<Document> search(final String query) {
        return documents.stream()
                        .filter(Query.parse(query))
                        .collect(Collectors.toList());
    }
}
