package com.lixinyu.cooperativecoding;

import com.lixinyu.cooperativecoding.data.RoleRepository;
import com.lixinyu.cooperativecoding.data.UserRepository;
import com.lixinyu.cooperativecoding.model.Role;
import com.lixinyu.cooperativecoding.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {

		SpringApplication.run(Application.class, args
		);
	}

//    @Bean
//    public CommandLineRunner table_init_role(RoleRepository repository){
//        return(args) -> {
//            repository.save(new Role("Super"));
//            repository.save(new Role("Normal"));
//            repository.save(new Role("Limited"));
//        };
//    }
//    @Bean
//    public CommandLineRunner db_init(UserRepository repository){
//        Set<Role> roles = new HashSet<>();
//        roles.add(new Role("Normal"));
//        roles.add(new Role("Super"));
//        //删除数据库并重建
//        return(args) -> {
//            //repository.save(new Role("Super"));
//            repository.save(new User("20143460",1,"张三丰",false,"20143460",roles,true));
//            repository.save(new User("20143461",1,"李新宇",false,"20143461",roles,true));
//        };
//    }
}
