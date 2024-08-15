package com.example.ex15_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ex15_jpa.entity.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, String>{
    
}
