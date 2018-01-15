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
    String s = "#include<stdio.h>\n" +
            "void main()\n" +
            "{\n" +
            "    char x[]=\"stack\";\n" +
            "    char y[]=\"overflow\";\n" +
            "    printf(\"set x:\\n\");\n" +
            "    scanf(\"%s\",x);\n" +
            "    printf(\"set y:\\n\");\n" +
            "    scanf(\"%s\",y);\n" +
            "    printf(\"well,x=%s,y=%s\",x,y);\n" +
            "}";
	private Code code = new Code(0,"helloworld",s,"c");

	@PostMapping(value = "/file")
	public @ResponseBody List<String> read(){
		List<String> lines = java.util.Arrays.asList(code.getContent().split("\n"));
		return lines;
	}
}