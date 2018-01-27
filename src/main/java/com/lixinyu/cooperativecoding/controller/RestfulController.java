package com.lixinyu.cooperativecoding.controller;

import com.lixinyu.cooperativecoding.data.CodeRepository;
import com.lixinyu.cooperativecoding.data.UserRepository;
import com.lixinyu.cooperativecoding.model.entity.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "http://127.0.0.1")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PreAuthorize("hasAnyRole('User')")
@RestController
public class RestfulController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CodeRepository codeRepository;


    @RequestMapping(value = "/file")
    public @ResponseBody
    Set<Code> jread(Authentication authentication) {
        return userRepository.findByUsername(authentication.getName()).get().getProject().getCodes();
    }

    @RequestMapping(value = "/code/{code_id}")
    public @ResponseBody
    String code(@PathVariable int code_id) {
        return codeRepository.findOne(code_id).getContent();
    }
}