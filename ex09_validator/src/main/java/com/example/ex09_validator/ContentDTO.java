package com.example.ex09_validator;

import lombok.Data;

@Data // art shift O 자동으로 필요한 라이브러리를 import 해주는 단축 키
public class ContentDTO {
    private int id;
    private String writer;
    private String content;
}
