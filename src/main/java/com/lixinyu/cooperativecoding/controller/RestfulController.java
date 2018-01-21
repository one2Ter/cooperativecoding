package com.lixinyu.cooperativecoding.controller;

import com.lixinyu.cooperativecoding.model.entity.Code;
import com.lixinyu.cooperativecoding.model.entity.Project;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

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
	private Code code = new Code(0,"helloworld",s,"c",new Project());

	@PostMapping(value = "/file")
	public @ResponseBody List<String> read(){
        return Arrays.asList(code.getContent().split("\n"));
	}
}