package com.lixinyu.cooperativecoding.controller;

import com.lixinyu.cooperativecoding.data.CodeRepository;
import com.lixinyu.cooperativecoding.data.ProjectRepository;
import com.lixinyu.cooperativecoding.data.UserRepository;
import com.lixinyu.cooperativecoding.model.entity.Code;
import com.lixinyu.cooperativecoding.model.entity.Project;
import com.lixinyu.cooperativecoding.model.entity.Team;
import com.lixinyu.cooperativecoding.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "http://127.0.0.1")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PreAuthorize("hasAnyRole('User')")
@RestController
public class RestfulController {
    private final UserRepository userRepository;
    private final CodeRepository codeRepository;
    private final ProjectRepository projectRepository;



    @Autowired
    public RestfulController(UserRepository userRepository, CodeRepository codeRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.codeRepository = codeRepository;
        this.projectRepository = projectRepository;
    }


    @RequestMapping(value = "/file")
    public @ResponseBody
    Set<Code> jread(Authentication authentication) {
        return userRepository.findByUsername(authentication.getName()).get().getProject().getCodes();
    }

    @RequestMapping(value = "/code/{code_id}")
    public @ResponseBody
    Code code(@PathVariable int code_id) {
        return codeRepository.findOne(code_id);
    }

    @GetMapping(value = "/project")
    public @ResponseBody
    Project project(Authentication authentication){
        Project project = userRepository.findByUsername(authentication.getName()).get().getProject();
        //统计在线人数
        int online = 0;
        for(User user:userRepository.findAll()){
            if(System.currentTimeMillis() - user.getLastHeartbeat() < 15000){
                online++;
            }
        }
        project.setOnline(online);
        return project;
    }

    @RequestMapping(value = "/project/all")
    public @ResponseBody
    Set<Project> projects(Authentication authentication){

        Team team = userRepository.findByUsername(authentication.getName()).get().getTeam();

        Set<Project> projects = team.getProjects();

        for(Project project:projects){
            //统计在线人数
            int online = 0;
            for(User user : userRepository.findAll()){
                if(user.getProject() == project && System.currentTimeMillis() - user.getLastHeartbeat() < 15000){
                    online++;
                }
            }
            project.setOnline(online);
            //projectRepository.save(project);
        }
        return projects;
    }

    @RequestMapping(value = "/code/new")
    public @ResponseBody
    Code codeNew(@RequestParam("code_title") String code_title, Authentication authentication){
        User user = userRepository.findByUsername(authentication.getName()).get();
        Code code = new Code(code_title,"","c",user.getProject(),false);
        codeRepository.save(code);
        return code;
    }

    @GetMapping(value="/user")
    public @ResponseBody
    User getUserInfo(Authentication authentication){
        return userRepository.findByUsername(authentication.getName()).get();
    }

    //Set project
    @RequestMapping(value="/user/project")
    public @ResponseBody
    User setUserInfo(@RequestParam("project_id") int project_id,Authentication authentication){
        User user = userRepository.findByUsername(authentication.getName()).get();
        Project project = projectRepository.findOne(project_id);

        user.setProject(project);
        userRepository.save(user);
        projectRepository.save(project);
        System.out.println(user.getProject().getProject_name());
        return user;
    }

    @GetMapping(value = "/project/current")
    public @ResponseBody
    long cproject(Authentication authentication){
        return userRepository.findByUsername(authentication.getName()).get().getProject().getProject_id();
    }
}