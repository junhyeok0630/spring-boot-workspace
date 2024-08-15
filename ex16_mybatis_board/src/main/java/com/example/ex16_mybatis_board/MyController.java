package com.example.ex16_mybatis_board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ex16_mybatis_board.dao.BoardDAO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyController {
    
    @Autowired
    BoardDAO dao;

    @RequestMapping("/")
    public String root() throws Exception {
        return "redirect:list";
    }

    @RequestMapping("/list")
    public String userlistPage(Model model) {
        model.addAttribute("list", dao.listDAO());
        return "list";
    }
    
    @RequestMapping("/view")
    public String view(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        model.addAttribute("dto", dao.viewDAO(id));
        return "view";
    }

    @RequestMapping("/write-form")
    public String writeForm() {
        return "write-form";
    }

    @RequestMapping("/write")
    public String write(Model model, HttpServletRequest request) {
        dao.writeDAO(request.getParameter("writer"), 
        request.getParameter("title"), 
        request.getParameter("content"));
        return "redirect:list";
    }

    @RequestMapping("/delete")
    public String delete(Model model, HttpServletRequest request) {
        dao.deleteDAO(request.getParameter("id"));
        return "redirect:list";
    }
}
