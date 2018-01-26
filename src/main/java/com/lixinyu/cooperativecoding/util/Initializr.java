package com.lixinyu.cooperativecoding.util;

import com.lixinyu.cooperativecoding.data.*;
import com.lixinyu.cooperativecoding.model.entity.Role;
import com.lixinyu.cooperativecoding.model.entity.Team;
import com.lixinyu.cooperativecoding.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

//初始化数据库
@Component
public class Initializr implements CommandLineRunner {
    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... strings) {
        //role
        roleRepository.save(new Role(0, "User"));
        roleRepository.save(new Role(1, "Administrator"));
        roleRepository.save(new Role(2, "Guest"));

        //team
        teamRepository.save(new Team(140103, "14计科3班"));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findOne(0));
        roles.add(roleRepository.findOne(1));
        //user
        userRepository.save(new User("20143461", teamRepository.findOne(140103), "李新宇", false, "20143461", roles, true));
    }
}
