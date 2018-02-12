package com.lixinyu.cooperativecoding.util;

import com.lixinyu.cooperativecoding.data.*;
import com.lixinyu.cooperativecoding.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

//初始化数据库(产生测试数据)
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
        initProject();
        initUser();
        initCode();
    }
    /*
    * Administrator 系统管理员
    * User          普通用户
    * Guest         游客
    */
    private void initRole() {
        roleRepository.save(new Role(0, "Administrator"));
        roleRepository.save(new Role(1, "User"));
        roleRepository.save(new Role(2, "Guest"));
    }

    private void initTeam() {
        teamRepository.save(new Team(140101, "14计科1班"));
        teamRepository.save(new Team(140102, "14计科2班"));
        teamRepository.save(new Team(140103, "14计科3班"));
    }

    private void initProject() {
        projectRepository.save(new Project(1, "helloworld", teamRepository.findOne(140103)));
    }

    private void initUser() {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findOne(0));
        userRepository.save(new User("20143461", teamRepository.findOne(140103), "李新宇", false, "20143461", roles, projectRepository.findOne(1), true));
        //userRepository.save(new User("20143460", teamRepository.findOne(140103), "张三丰", false, "20143460", roles, projectRepository.findOne(1), true));

        //为李新宇添加管理员权限
        User user = userRepository.findByUsername("20143461").get();
        user.addRole(roleRepository.findOne(1));
        userRepository.save(user);
    }

    private void initCode() {
        String s = "#include <stdio.h>\n" +
                "int main()\n" +
                "{\n" +
                "    int i,j,k;\n" +
                "    for(i=0;i<=3;i++) {\n" +
                "        for(j=0;j<=2-i;j++) {\n" +
                "            printf(\" \");\n" +
                "        }\n" +
                "        for(k=0;k<=2*i;k++) {\n" +
                "            printf(\"*\");\n" +
                "        }\n" +
                "        printf(\"\\n\");\n" +
                "    }\n" +
                "    for(i=0;i<=2;i++) {\n" +
                "        for(j=0;j<=i;j++) {\n" +
                "            printf(\" \");\n" +
                "        }\n" +
                "        for(k=0;k<=4-2*i;k++) {\n" +
                "            printf(\"*\");\n" +
                "        }\n" +
                "        printf(\"\\n\");\n" +
                "    }\n" +
                "  \n" +
                "}";

        codeRepository.save(new Code(1, "main.c", s, "c", projectRepository.findOne(1), true));
        codeRepository.save(new Code(2, "readme.md", "毕业设计readme", "text", projectRepository.findOne(1), false));
    }
}
