package com.example.ex18_mybatis_result_num.dto;

import lombok.Data;

@Data
public class BoardDTO {
    private int id;
    private String writer;
    private String title;
    private String content;
}
