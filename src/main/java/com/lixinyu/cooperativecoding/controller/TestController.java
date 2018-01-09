package com.lixinyu.cooperativecoding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {


    @GetMapping("/hello")
    public String hello(HttpServletRequest request, Model model){
        model.addAttribute("message","hello world");
        return "hello";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }


}
