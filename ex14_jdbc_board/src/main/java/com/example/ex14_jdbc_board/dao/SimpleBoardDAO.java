package com.example.ex14_jdbc_board.dao;

import java.util.List;

import com.example.ex14_jdbc_board.dto.SimpleBoardDTO;

public interface SimpleBoardDAO {

    public List<SimpleBoardDTO> listDAO();
    
    public SimpleBoardDTO viewDAO(String id);

    public int writeDAO(String writer, String title, String content);
    
    public int deleteDAO(String id);
} 