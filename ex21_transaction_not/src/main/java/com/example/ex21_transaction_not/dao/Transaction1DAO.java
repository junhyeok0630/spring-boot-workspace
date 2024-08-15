package com.example.ex21_transaction_not.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Transaction1DAO {
    public void pay(String consumerId, int amount);
}
