package com.example.ex06_model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
    
    @RequestMapping("/")
    public @ResponseBody String root() throws Exception {
        return "Model & View";
    }

    @RequestMapping("/test1")
    public String test1(Model model) {
        model.addAttribute("name", "김일남");
        model.addAttribute("age", 99);
        return "test1";
    }
    
    @RequestMapping("/mv")
    public ModelAndView test2(Model model) {
        ModelAndView mv = new ModelAndView();

        List<String> list = new ArrayList<>();

        list.add("test1");
        list.add("test2");
        list.add("test3");
        mv.addObject("lists", list);
        mv.addObject("ObjectTest", "테스트");
        mv.addObject("name", "김이남");
        mv.addObject("age", 98);
        mv.setViewName("view/myView");
        return mv;
    }
}
