package com.example.ex24_transaction_propagation.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Transaction3DAO {
    public void pay(String consumerId, int amount);
}
