package com.example.ex13_jdbc.jdbc;

import lombok.Data;

//DTO 객체 : 계층 간 데이터 전송을 위해 사용되는 객체
    // ㄴ 주로 비즈니스 로직 없이 데이터 전달만을 목적
    // ㄴ 데이터베이스 엔티티와 뷰 사이의 데이터 변환에 사용
    // ㄴ 필요한 데이터만 선택적으로 전송 가능
    //CTO : 커맨드 객체 / 주로 폼 데이터를 받아올 때 사용
    // ㄴ 클라이언트로부터 받은 요청 데이터를 담는 객체
    // ㄴ 보통 컨트롤러의 메소드 파라미터로 사용
    // ㄴ 유효성 검사를 적용하기 쉬움
    //DAO : 데이터베이스 또는 기타 영속성 메커니즘과의 상호작용을 추상화하고 캡슐화하는 객체
@Data    
public class MyUserDTO {
    private String id;
    private String name;
}
