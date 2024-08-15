package com.example.ex10_validation_utils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
    
    @RequestMapping("/")
    public @ResponseBody String root() {
        return "Validation (1)";
    }

    @RequestMapping("/insert-form")
    public String insert1(@ModelAttribute("dto") ContentDTO contentDto) {
        return "createPage";
    }
    // @RequestMapping("/insert-form")
    // public String insert1(Model model) {
    //     model.addAttribute("dto", new ContentDTO());
    //     return "createPage";
    // }

    @RequestMapping("/create")
    public String insert2(@ModelAttribute("dto") ContentDTO contentDto, BindingResult result) {
        String page = "createDonePage";
        
        ContentValidator validator = new ContentValidator();
        validator.validate(contentDto, result);

        System.out.println("validation result : " + result.hasErrors());

        if(result.hasErrors()) {
            page = "createPage";
        }
        
        return page;
    }
}
