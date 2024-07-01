package com.iteratrlearning.shu_book.chapter_04;

public class Attributes {
    
    // 변수에 final : 값 변경 불가
    // static : 클래스가 한 개의 인스턴스만 갖도록 지정
    public static final String PATH = "path";		
    public static final String PATIENT = "patient";	// 환자명을 나타내는 key 값으로 사용
    public static final String ADDRESS = "address";	// 환자 주소를 나타내는 key 값으로 사용
    public static final String BODY = "body";		// 임포트 파일의 본문을나타내는 key 값으로 사용
    public static final String WIDTH = "width";		// 이미지(xray) 파일의 폭, 높이를 나타내는 key 값으로 사용
    public static final String HEIGHT = "height";	
    public static final String TYPE = "type";		// 임포트 될 파일의 유형("IMAGE", "REPORT", "INVOICE", "LETTER")을 key 값으로 사용
    public static final String AMOUNT = "amount";	// 치료 비용 청구서(INVOICE) 파일의 청구 금액을 key 값으로 사용
}
