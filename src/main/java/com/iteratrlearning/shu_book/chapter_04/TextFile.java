/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.iteratrlearning.shu_book.chapter_04;

import static java.util.stream.Collectors.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

class TextFile {
    private final Map<String, String> attributes;
        private final List<String> lines;	// 파일의 내용을 읽어서 각 행을 하나의 List 요소로 저장(원본을 배열로 저장)
    
        // class continues ...
    // end::classDefinition[]
    
        TextFile(final File file) throws IOException {
            attributes = new HashMap<>();
            attributes.put(PATH, file.getPath());
            lines = Files.lines(file.toPath()).collect(toList());
        }
    
        Map<String, String> getAttributes() {
            return attributes;
        }
    
        // tag::addLines[]
        /*
         * start : 라인을 읽을 시작 위치(행 번호)
         * isEnd : 조건을 검사하는 람다식
         *      - isEnd.test(line) 
         *          : 주어진 line 이 특정 조건을 만족하는지 확인
         *            ReportImporter 의 경우, 항상 false 를 반환하므로 조건을 만족치 않아 누적이 계속됨
         *            LetterImporter 의 경우, line 이 "regards," 로 시작하면 true 를 반환
         * attributeName : 속성명
         */
        int addLines(
            final int start,
            final Predicate<String> isEnd,
            final String attributeName) {
    
            final StringBuilder accumulator = new StringBuilder();
            int lineNumber;
            for (lineNumber = start; lineNumber < lines.size(); lineNumber++) {
                final String line = lines.get(lineNumber);
                if (isEnd.test(line)) {
                    break;
                }
    
                accumulator.append(line);
                accumulator.append("\n");
            }
            attributes.put(attributeName, accumulator.toString().trim());
            return lineNumber;
        }
        // end::addLines[]
    
        // tag::addLineSuffix[]
        void addLineSuffix(final String prefix, final String attributeName) {
            for(final String line: lines) {
                if (line.startsWith(prefix)) {
                    attributes.put(attributeName, line.substring(prefix.length()));
                    break;
                }
            }
        }
}
