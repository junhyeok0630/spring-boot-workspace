package com.example.ex15_jpa_error_controll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ex15_jpa_error_controll.entity.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, String>{
    
}
