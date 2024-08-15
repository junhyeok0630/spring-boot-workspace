package com.example.ex20_service.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ex20_service.dao.BoardDAO;
import com.example.ex20_service.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDAO dao;

    @Override
    public List<BoardDTO> list() {
        return dao.listDAO();
    }

    @Override
    public BoardDTO view(String id) {
        return dao.viewDAO(id);
    }

    @Override
    public int write(Map<String, String> map) {
        int result = dao.writeDAO(map);
        return result;
    }

    @Override
    public int delete(@Param("_id")String id) {
        int result = dao.deleteDAO(id);
        return result;
    }

    @Override
    public int count(){
        int totalCount = dao.articleCount();
        return totalCount;
    }

}
