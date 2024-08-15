package com.example.ex17_mybatis_param.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.ex17_mybatis_param.dto.BoardDTO;

@Mapper
public interface BoardDAO {
    public List<BoardDTO> listDAO();
    
    public BoardDTO viewDAO(String id);

    public int writeDAO(String writer, String title, String content);
    
    public int deleteDAO(@Param("_id")String id);

} 