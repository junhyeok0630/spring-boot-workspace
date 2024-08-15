package com.example.ex14_jdbc_board.dto;

import lombok.Data;

@Data
public class SimpleBoardDTO {
    private int id;
    private String writer;
    private String title;
    private String content;
}
