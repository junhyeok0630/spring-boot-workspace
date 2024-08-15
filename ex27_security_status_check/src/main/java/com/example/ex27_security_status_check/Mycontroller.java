package com.example.ex27_security_status_check;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Mycontroller {
    
    @RequestMapping("/")
    public @ResponseBody String root() throws Exception {
        return "Secutiry (1)";
    }

    @RequestMapping("/guest/welcome")
    public String welcome1() {
        return "guest/welcome1";
    }

    @RequestMapping("/member/welcome")
    public String welcome2() {
        return "member/welcome2";
    }

    @RequestMapping("/admin/welcome")
    public String welcome3() {
        return "admin/welcome3";
    }

    @RequestMapping("/login-form")
    public String loginForm() {
        return "security/login-form";
    }

    @RequestMapping("/login-error")
    public String loginError() {
        return "security/login-error";
    }
}
