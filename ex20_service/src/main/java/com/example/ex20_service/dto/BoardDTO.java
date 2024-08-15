package com.example.ex20_service.dto;

import lombok.Data;

@Data
public class BoardDTO {
    private int id;
    private String writer;
    private String title;
    private String content;
}
