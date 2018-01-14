package com.lixinyu.cooperativecoding.controller;

import java.util.List;

import com.lixinyu.cooperativecoding.model.Code;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PreAuthorize("hasAnyRole('Normal')")
@RestController
public class RestfulController {
	private Code code = new Code(0,"helloworld","#include<stdio.h>\nint main()\n{\n    printf(\"hello world\");\n    return 0;\n}","c");

	@PostMapping(value = "/file")
	public @ResponseBody List<String> read(){
		List<String> lines = java.util.Arrays.asList(code.getContent().split("\n"));
		return lines;
	}
}