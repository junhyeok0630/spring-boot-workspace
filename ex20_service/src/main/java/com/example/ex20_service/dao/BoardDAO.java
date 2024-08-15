package com.example.ex20_service.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.ex20_service.dto.BoardDTO;

@Mapper
public interface BoardDAO {
    public List<BoardDTO> listDAO();
    
    public BoardDTO viewDAO(String id);

    public int writeDAO(Map<String, String> map);
    
    public int deleteDAO(@Param("_id")String id);

    public int articleCount();
} 