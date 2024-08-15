package com.example.ex07_form;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyController {

    @RequestMapping("/")
    public @ResponseBody String root() throws Exception {
        return "Form 데이터 전달받아 사용하기";
    }

    @RequestMapping("/test1")
    public String test1(HttpServletRequest httpServletRequest, Model model) {
        String id = httpServletRequest.getParameter("id");
        String name = httpServletRequest.getParameter("name");

        model.addAttribute("id", id);
        model.addAttribute("name", name);
        return "test1";
    }

    @RequestMapping("/test2")
    public String test2(@RequestParam("id") String id, @RequestParam("name") String name, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        return "test2";
    }

    @RequestMapping("/test3")
    public String test3(Member member, Model model) {
        return "test3";
    }
    @RequestMapping("/test4/{studentId}/{name}")
    public String test4(@PathVariable String studentId, @PathVariable String name, Model model) {
        model.addAttribute("id", studentId);
        model.addAttribute("name", name);
        return "test4";
    }
}
