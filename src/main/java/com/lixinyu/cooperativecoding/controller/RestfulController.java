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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private ProjectRepository projectRepository;


    @RequestMapping(value = "/file")
    public @ResponseBody
    Set<Code> jread(Authentication authentication) {
        return userRepository.findByUsername(authentication.getName()).get().getProject().getCodes();
    }

    @RequestMapping(value = "/code/{code_id}")
    public @ResponseBody
    String code(@PathVariable int code_id) {
        return codeRepository.findOne(code_id).getContent();
    }

    @RequestMapping(value = "/project")
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
}