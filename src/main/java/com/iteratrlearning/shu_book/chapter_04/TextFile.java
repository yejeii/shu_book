package com.iteratrlearning.shu_book.chapter_04;

import static com.iteratrlearning.shu_book.chapter_04.Attributes.*;
import static java.util.stream.Collectors.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * 텍스트 형식의 파일 내용을 처리하는 책임을 지는 도메인 클래스
 * 
 * p.101
 * "문서가 항상 텍스트 파일이라는 보장이 없으므로 TextFile은 Document의 서브클래스가 아니다" 의 의미
 *  - Document 클래스는 모든 확장자의 데이터를 저장하는 클래스
 *  - TextFile 클래스는 텍스트 파일의 데이터를 처리하는 클래스
 *  
 * is-a 관계가 성립하지 못하기 때문에 서브 클래스가 아니다.
 */
class TextFile {
    private final Map<String, String> attributes;
    private final List<String> lines;	// 파일의 내용을 읽어서 각 행을 하나의 List 요소로 저장(원본을 배열로 저장)

    TextFile(final File file) throws IOException {
        attributes = new HashMap<>();
        attributes.put(PATH, file.getPath());
        lines = Files.lines(file.toPath()).collect(toList());
    }

    Map<String, String> getAttributes() {
        return attributes;
    }

    /**
     *   
     * 
     * 각 텍스트 관련 임포터 클래스에 존재했던 텍스트 추출 기능을 공유시키기 위해 TextFile 클래스로 뽑아왔을 것..
     * 텍스트 추출 기능을 공통화시키기 위해 일부 로직을 추상화함(Predicate)
     * 
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

    void addLineSuffix(final String prefix, final String attributeName) {
        for(final String line: lines) {
            if (line.startsWith(prefix)) {
                attributes.put(attributeName, line.substring(prefix.length()));
                break;
            }
        }
    }
}
