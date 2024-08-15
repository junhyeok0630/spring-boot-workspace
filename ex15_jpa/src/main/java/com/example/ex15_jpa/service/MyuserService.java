package com.example.ex15_jpa.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ex15_jpa.entity.MyUser;
import com.example.ex15_jpa.repository.MyUserRepository;

@Service
public class MyuserService {

    @Autowired
    private MyUserRepository myUserRepository;

    public List<MyUser> list() {
        return myUserRepository.findAll();
    }
}
