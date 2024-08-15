package com.example.ex14_jdbc_board.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.ex14_jdbc_board.dto.SimpleBoardDTO;

@Repository
public class SimpleBoardImpl implements SimpleBoardDAO {
   
    @Autowired
    JdbcTemplate template;

    @Override
    public List<SimpleBoardDTO> listDAO() {
        System.out.println("listDAO()");

        String query = "select * from simple_board order by id desc";
        List<SimpleBoardDTO> list = template.query(query, new BeanPropertyRowMapper<SimpleBoardDTO>(SimpleBoardDTO.class));

        return list;
    }

    @Override
    public SimpleBoardDTO viewDAO(String id) {
        System.out.println("viewDAO()");

        String query = "select * from simple_board where id = " + id;
        SimpleBoardDTO dto = template.queryForObject(query, new BeanPropertyRowMapper<SimpleBoardDTO>(SimpleBoardDTO.class));

        return dto;
    }

    @Override
    public int writeDAO(String writer, String title, String content) {
        System.out.println("writeDAO()");

        String query = "insert into simple_board(writer, title, content) values (?, ?, ?)";
        return template.update(query, writer, title, content);
    }

    @Override
    public int deleteDAO(String id) {
        System.out.println("deleteDAO()");
        
        String query = "delete from simple_board where id = ?";
        return template.update(query,Integer.parseInt(id));
    }
}
