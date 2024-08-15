package com.example.ex17_mybatis_param.dto;

import lombok.Data;

@Data
public class BoardDTO {
    private int id;
    private String writer;
    private String title;
    private String content;
}
