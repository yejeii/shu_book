package com.iteratrlearning.shu_book.chapter_02;

/**
 * 애플리케이션의 기능
 * - bank-data-simple.csv 의 내용을 읽어서, 고객이 원하는 정보를 리포트로 제공
 * 
 * 현재 개발된 애플리케이션의 문제점
 * - 1. 하나의 메서드에 관계없는 많은 기능이 구현되어 있음 : 낮은 응집도
 * 		-> 향후 각 기능에 대해 신규 요구사항 및 기능 확장 시 현재의 메서드의 코드가 계속 증가하게 됨 : 갓 클래스
 * 		-> 향후 기능 파악과 구분이 힘들어지게 됨.. 유지보수 Hard... : 갓 클래스 안티 패턴
 * 
 * - 2. 현재는 CSV 형식의 파일만 가능.. : 확장성 X
 * 	    -> 다양한 형식의 파일에 대한 대응 불가
 * 		-> 새로운 형식의 파일에 대한 요구사항이 발생하게 될 가능성 매우 High	: 유지보수 Hard
 * 
 * - 3. CSV 로부터 추출된 정보의 재사용, 사용 편리성, 유리 보수성 등이 낮음
 * 
 * - 4. 3번과 연결됨
 * 	    -> CSV 파일의 칼럼 위치 변경에 대한 대응이 쉽지 않음
 * 		-> 4번의 문제점을 고려해서, 3번에서 함께 고민하면 좋음
 * 
 * 과거의 해결 이력
 * 	- ver 1
 * 	  BankStatementAnalyzerSimple.java 시작
 * 	  요구사항 접수 -> 수정 및 반영
 * 
 *  - ver 2
 *    BankStatementAnalyzerProblematic.java 
 *    수정을 생각없이 복사 & 붙이기
 *  
 *  - ver 3
 *    BankStatementAnalyzerSRP.java
 *    무늬만 SRP ...
 *    각각의 기능을 각각 책임지도록 메서드로 분리함
 *    메서드별로 책임을 지고 있음 -> 클래스별로 책임을 지도록 수정해야 함
 *    -> 무늬만 SRP 에서 정말 SRP 스러워짐(리펙토링)
 *    
 *  - ver 4
 *    MainApplication.java
 *    많이 SRP 스러워짐
 *    
 *    각각의 책임(기능)이 class 별로 나누어짐 -> 응집도 높아짐
 *    
 * 
 * 
 * 문제점의 해결 방향
 * 1. SOLID 의 SRP ( 단일 책임 원칙 )
 * 	- 한 클래스는 한 기능만 책임(기능)을 진다.
 *  - 클래스가 변경 및 수리되어야 하는 이유는 오직 하나여야 한다.
 *  - 응집도가 높아지고, 결합도를 낮추기를 원하지만, 결합도는 다음에...
 *  
 */
public class MainApplication {

    public static void main(String[] args) throws Exception {

    	// 기능이 메서드에서 클래스로 변화된 것이 감지됨
    	
    	//  입출금 내역을 분석하는 책임
        final BankStatementAnalyzerFinal bankStatementAnalyzer = new BankStatementAnalyzerFinal();

        // CSV 파일 파싱에 필요한 parser 지정
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();

        // bankStatementAnalyzer.analyze("bank-data-simple.csv", bankStatementParser);

        /* 
         * 10차
         * 보고서를 HTML 포맷으로 생성
         * 향후에 excel 포맷으로 새로운 요구 사항이 발생한다면...
         * -> 느슨한 결합으로 구현 (약한 결합)
         */
        final Exporter exporter = new HtmlExporter();
        bankStatementAnalyzer.analyze("bank-data-simple.csv", bankStatementParser, exporter);

    }
}
