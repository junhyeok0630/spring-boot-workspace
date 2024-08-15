package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    
    @RequestMapping("/hello") // /hello 브라우저 요청 시 실행
    @ResponseBody // 브라우저 바디에 리턴 값을 응답한다.
    public String hello() {
        System.out.println("Hello World");
        return "Hello Spring Boot New!";
    }
}
