package com.example.ex20_service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ex20_service.dao.BoardDAO;
import com.example.ex20_service.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyController {
    
    // @Autowired
    // BoardDAO dao;

    @Autowired
    BoardService boardService;

    @RequestMapping("/")
    public String root() throws Exception {
        return "redirect:list";
    }

    @RequestMapping("/list")
    public String userlistPage(Model model) {
        model.addAttribute("list", boardService.list());

        int totalCount = boardService.count();
        System.out.println("Count : " + totalCount);

        model.addAttribute("totalCount", totalCount);

        return "list";
    }
    
    @RequestMapping("/view")
    public String view(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        model.addAttribute("dto", boardService.view(id));
        return "view";
    }

    @RequestMapping("/write-form")
    public String writeForm() {
        return "write-form";
    }

    @RequestMapping("/write")
    public String write(Model model, HttpServletRequest request) {
        String name = request.getParameter("writer");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        Map<String, String> map = new HashMap<>();
        map.put("item1", name);
        map.put("item2", title);
        map.put("item3", content);

        int result = boardService.write(map);
        System.out.println("Write : " + result);
        return "redirect:list";
    }

    @RequestMapping("/delete")
    public String delete(Model model, HttpServletRequest request) {
        boardService.delete(request.getParameter("id"));
        return "redirect:list";
    }
}
