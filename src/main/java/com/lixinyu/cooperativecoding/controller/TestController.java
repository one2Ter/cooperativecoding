package com.lixinyu.cooperativecoding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {
    //自定义登录页实现
    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model){
        return "login";
    }


}
