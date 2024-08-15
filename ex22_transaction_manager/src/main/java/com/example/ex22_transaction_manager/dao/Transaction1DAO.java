package com.example.ex22_transaction_manager.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Transaction1DAO {
    public void pay(String consumerId, int amount);
}
