package com.example.ex13_jdbc.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MyUserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<MyUserDTO> list() {
        String query = "select * from myuser"; 

        // SQL 결과 집합의 각 행을 MyUserDTO 클래스의 객체로 매핑(MyUserDTO 클래스의 프로퍼티와 SQL 결과 집합의 컬럼 이름을 자동으로 연결)
        List<MyUserDTO> list = jdbcTemplate.query(query, new BeanPropertyRowMapper<MyUserDTO>(MyUserDTO.class));
        
        return list;
    }
}
