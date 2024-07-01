package com.iteratrlearning.shu_book.chapter_04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DocumentManagementSystem {

    private final List<Document> documents = new ArrayList<>();
    private final Map<String, Importer> extensionToImporter = new HashMap<>();

    public DocumentManagementSystem() {
        // extensionToImporter.put("letter", new LetterImporter());
        // extensionToImporter.put("report", new ReportImporter());
        extensionToImporter.put("jpg", new ImageImporter());
    }
    
    void importFile(String path) {

    }

    List<Document> contents() {

        return null;
    }

    // 1차. 검색 기능
    public List<Document> search(final String query) {
        return documents.stream()
                        .filter(Query.parse(query))
                        .collect(Collectors.toList());
    }
}
