package com.example.ex16_mybatis_board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.ex16_mybatis_board.dto.BoardDTO;

@Mapper
public interface BoardDAO {
    public List<BoardDTO> listDAO();
    
    public BoardDTO viewDAO(String id);

    public int writeDAO(String writer, String title, String content);
    
    public int deleteDAO(String id);

} 