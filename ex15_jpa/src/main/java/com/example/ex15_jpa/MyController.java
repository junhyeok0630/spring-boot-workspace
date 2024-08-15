package com.example.ex15_jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ex15_jpa.service.MyuserService;




@Controller
public class MyController {
    
    @Autowired
    private MyuserService myuserService;

    @RequestMapping("/")
    public @ResponseBody String root() throws Exception {
        return "Jpa 사용하기";
    }

    // @RequestMapping(value = "/user", method = RequestMethod.GET)
    @GetMapping(value = "/user") // 위 문장과 동일, 더 자주 사용함
    public String userlistPage(Model model) {
        model.addAttribute("users", myuserService.list());
        return "userlist";
    }
}
