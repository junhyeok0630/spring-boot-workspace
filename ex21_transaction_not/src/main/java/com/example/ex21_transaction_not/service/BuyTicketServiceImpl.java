package com.example.ex21_transaction_not.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ex21_transaction_not.dao.Transaction1DAO;
import com.example.ex21_transaction_not.dao.Transaction2DAO;

@Service
public class BuyTicketServiceImpl implements BuyTicketService{

    @Autowired
    Transaction1DAO transaction1DAO;

    @Autowired
    Transaction2DAO transaction2DAO;

    @Override
    public int buy(String consumerId, int amount, String error) {
        try {
            transaction1DAO.pay(consumerId, amount);

            // 의도적 에러
            if(error.equals("1")) {
                int n = 10 / 0;
            }

            transaction2DAO.pay(consumerId, amount);
            return 1;
        } catch(Exception e) {
            return 0;
        }
    }
    
}
