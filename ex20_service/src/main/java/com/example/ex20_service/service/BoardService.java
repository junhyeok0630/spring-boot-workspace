package com.example.ex20_service.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.ex20_service.dto.BoardDTO;

public interface BoardService {
    public List<BoardDTO> list();
    
    public BoardDTO view(String id);

    public int write(Map<String, String> map);
    
    public int delete(@Param("_id")String id);

    public int count();
}
