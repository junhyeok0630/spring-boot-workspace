package com.example.ex15_mybatis.jdbc;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper // Mapper를 통해 xml에서 사용가능
public interface MyUserDAO {
    List<MyUserDTO> list();
}
