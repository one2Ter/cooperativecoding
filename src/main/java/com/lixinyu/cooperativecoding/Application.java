package com.lixinyu.cooperativecoding;

import com.lixinyu.cooperativecoding.Tools.Environment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
	    //初始化数据库和服务端组件
		Environment.init();
		SpringApplication.run(Application.class, args
		);
	}
}
