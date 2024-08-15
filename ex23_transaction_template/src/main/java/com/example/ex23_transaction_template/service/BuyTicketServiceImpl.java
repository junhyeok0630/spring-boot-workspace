package com.example.ex23_transaction_template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.ex23_transaction_template.dao.Transaction1DAO;
import com.example.ex23_transaction_template.dao.Transaction2DAO;

@Service
public class BuyTicketServiceImpl implements BuyTicketService{

    @Autowired
    Transaction1DAO transaction1DAO;

    @Autowired
    Transaction2DAO transaction2DAO;

    // @Autowired
    // PlatformTransactionManager transactionManager;

    // @Autowired
    // TransactionDefinition transactionDefinition;

    @Autowired
    TransactionTemplate transactionTemplate;
    
    @Override
    public int buy(String consumerId, int amount, String error) {

        // TransactionStatus status = transactionManager.getTransaction(transactionDefinition);

        try {
            transactionTemplate.execute(
                new TransactionCallbackWithoutResult() {

                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus arg0) {
                        transaction1DAO.pay(consumerId, amount);
                        // 의도적 에러
                        if(error.equals("1")) {
                        int n = 10 / 0;
                        }
    
                        transaction2DAO.pay(consumerId, amount);
                    }
                }
            );


            // transactionManager.commit(status);

            return 1;
        } catch(Exception e) {
            System.out.println("Rollback!!");
            // transactionManager.rollback(status);
            return 0;
        }
    }
    
}
