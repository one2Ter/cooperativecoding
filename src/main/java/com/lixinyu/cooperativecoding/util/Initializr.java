package com.lixinyu.cooperativecoding.util;

import com.lixinyu.cooperativecoding.data.*;
import com.lixinyu.cooperativecoding.model.entity.*;
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
        initRole();
        initTeam();
        initUser();
        initProject();
        initCode();
    }

    private void initRole() {
        roleRepository.save(new Role(0, "User"));
        roleRepository.save(new Role(1, "Administrator"));
        roleRepository.save(new Role(2, "Guest"));
    }

    private void initTeam() {
        teamRepository.save(new Team(140101, "14计科1班"));
        teamRepository.save(new Team(140102, "14计科2班"));
        teamRepository.save(new Team(140103, "14计科3班"));
    }

    private void initUser() {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findOne(0));
        userRepository.save(new User("20143461", teamRepository.findOne(140103), "李新宇", false, "20143461", roles, true));

        //为李新宇添加管理员权限
        User user = userRepository.findByUsername("20143461").get();
        user.addRole(roleRepository.findOne(1));
        userRepository.save(user);
    }

    private void initProject() {
        projectRepository.save(new Project(1, "helloworld", teamRepository.findOne(140103)));
    }

    private void initCode() {
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
        codeRepository.save(new Code(1, "main.c", s, "c", projectRepository.findOne(1)));
    }

}
