package com.lixinyu.cooperativecoding.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {
    //自定义登录页实现
    @GetMapping("/login")
    public String login_get(){
        return "login";
    }

    @GetMapping("/")
    public String session(HttpServletRequest request, Model model,Authentication authentication){
        //String name = SecurityContextHolder.getContext().getAuthentication().getName();
        String name = authentication.getName();
        request.getSession().setAttribute("content", name);
        model.addAttribute("content",request.getSession().getAttribute("content"));
        return "index";
    }

}
