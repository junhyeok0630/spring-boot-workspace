package com.example.ex24_transaction_propagation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ex24_transaction_propagation.service.BuyAndLogServiceImpl;

@Controller
public class Mycontroller {

    @Autowired
    BuyAndLogServiceImpl buyAndLogServiceImpl;

    @RequestMapping("/")
    public @ResponseBody String root() throws Exception {
        return "Transaction Propagation";
    }

    @RequestMapping("/buy-ticket")
    public String buyTicket() {
        return "buy-ticket";
    }

    @RequestMapping("/buy-ticket-card")
    public String buyTicketCard(@RequestParam("consumerId") String consumerId, 
                                @RequestParam("amount") String amount, 
                                @RequestParam("error") String error, 
                                Model model) {

        int result = buyAndLogServiceImpl.buy(consumerId, Integer.parseInt(amount), error);

        model.addAttribute("consumerId", consumerId);
        model.addAttribute("amount", amount);   
        
        if (result == 1) {
            return "buy-ticket-end";
        } else {
            return "buy-ticket-error";
        }
    }
}
