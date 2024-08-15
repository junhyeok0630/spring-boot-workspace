package com.example.ex21_transaction_not;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ex21_transaction_not.service.BuyTicketService;

@Controller
public class Mycontroller {

    @Autowired
    BuyTicketService buyTicketService;

    @RequestMapping("/")
    public @ResponseBody String root() throws Exception {
        return "Transaction 미적용";
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

        int result = buyTicketService.buy(consumerId, Integer.parseInt(amount), error);

        model.addAttribute("consumerId", consumerId);
        model.addAttribute("amount", amount);   
        
        if (result == 1) {
            return "buy-ticket-end";
        } else {
            return "buy-ticket-error";
        }
    }
}
