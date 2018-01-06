package com.lixinyu.cooperativecoding.Controller;

import java.util.List;

import com.lixinyu.cooperativecoding.Entries.Code;
import com.lixinyu.cooperativecoding.Tools.Compiler;
import com.lixinyu.cooperativecoding.Editor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://127.0.0.1")
@RestController
public class TestController {
	Code code = new Code(0,"#include<stdio.h>\n int main()\n{\n    printf(\"hello world\");\n    return 0;\n}");

	@PostMapping("/run")
	public @ResponseBody String run(){
		String output = "init";
		try {
			output =  Compiler.build(Editor.getFileWithCode(code));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	
	@PostMapping("/file")
	public @ResponseBody List<String> read(){
		
		List<String> lines = java.util.Arrays.asList(code.content.split("\n"));
		return lines;
	}
	
	@PostMapping("/update")
	public @ResponseBody String update(@RequestParam String data,@RequestParam String path) {
		List<String> lines = java.util.Arrays.asList(data.split("\n"));
		code.content=data;
		Editor.writeStringArrListintoFile(lines,path);
		return "RE:"+data;
	}
}
