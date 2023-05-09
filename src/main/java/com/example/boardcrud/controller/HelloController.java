package com.example.boardcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("username", "dongwook");
        return "hello";
    }

    @GetMapping("/bye")
    public String bye(Model model) {
        model.addAttribute("nickname", "동욱");
        return "goodbye";
    }
}
