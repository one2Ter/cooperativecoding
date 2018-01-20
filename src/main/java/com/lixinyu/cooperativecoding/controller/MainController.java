package com.lixinyu.cooperativecoding.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://127.0.0.1")
@Controller
public class MainController{
    //自定义登录页实现
    @GetMapping("/login")
    public String login_get() {
        return "login";
    }

    @GetMapping("/")
    public String session(HttpServletRequest request, Model model, Authentication authentication) {
        String username = authentication.getName();

        request.getSession().setAttribute("username", username);
        model.addAttribute("username", request.getSession().getAttribute("username"));
        return "index";
    }
}