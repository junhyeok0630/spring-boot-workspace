package com.example.ex24_transaction_propagation.service;

public interface BuyTicketService {
    public int buy(String consumerId, int money, String error);
}
