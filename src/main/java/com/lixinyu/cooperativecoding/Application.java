package com.lixinyu.cooperativecoding;

import com.lixinyu.cooperativecoding.Entries.User;
import com.lixinyu.cooperativecoding.Entries.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args
		);
	}

    @Bean
    public CommandLineRunner db_init(UserRepository repository){
        //删除数据库并重建
        return(args) -> {
            repository.save(new User(20143461,1,"李新宇",0));
        };
    }
}
