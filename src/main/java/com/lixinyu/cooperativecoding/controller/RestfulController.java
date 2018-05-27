package com.lixinyu.cooperativecoding.controller;

import com.lixinyu.cooperativecoding.data.CodeRepository;
import com.lixinyu.cooperativecoding.data.ProjectRepository;
import com.lixinyu.cooperativecoding.data.TeamRepository;
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

import java.util.ArrayList;
import java.util.Set;

@CrossOrigin(origins = "http://127.0.0.1")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PreAuthorize("hasAnyRole('User','Administrator')")
@RestController
public class RestfulController {
    private final UserRepository userRepository;
    private final CodeRepository codeRepository;
    private final ProjectRepository projectRepository;
    private final TeamRepository teamRepository;



    @Autowired
    public RestfulController(UserRepository userRepository, CodeRepository codeRepository, ProjectRepository projectRepository, TeamRepository teamRepository) {
        this.userRepository = userRepository;
        this.codeRepository = codeRepository;
        this.projectRepository = projectRepository;
        this.teamRepository = teamRepository;
    }


    @RequestMapping(value = "/code/all")
    public @ResponseBody
    Set<Code> jread(Authentication authentication) {
        return userRepository.findByUsername(authentication.getName()).get().getProject().getCodes();
    }

    @RequestMapping(value = "/code/{code_id}")
    public @ResponseBody
    Code code(@PathVariable int code_id) {
        return codeRepository.findOne(code_id);
    }

    @RequestMapping(value = "/project")
    public @ResponseBody
    Project project(Authentication authentication){
        Project project = userRepository.findByUsername(authentication.getName()).get().getProject();
        int online = 0;
        for(User user:userRepository.findAll()){
            if(System.currentTimeMillis() - user.getLastHeartbeat() < 15000){
                online++;
            }
        }
        project.setOnline(online);

        if (project.getMaintainer()!=null){
            if(System.currentTimeMillis() - project.getMaintainer().getLastHeartbeat() > 30000){
                project.setMaintainer(null);
            }
        }

        projectRepository.save(project);
        return project;
    }

    @RequestMapping(value = "/projects")
    public @ResponseBody
    ArrayList<Project> allProjects(@RequestParam("team_id") int team_id){
        ArrayList<Project> projects = new ArrayList<>();
        System.out.println("team_id = "+team_id);
        if(team_id>0){
            projects = projectRepository.findAllByTeam(teamRepository.findOne(team_id));
        }else{
            projectRepository.findAll().forEach(projects::add);
        }

        return projects;
    }
    @GetMapping(value = "/users")
    public @ResponseBody
    ArrayList<User> allUsers(){
        ArrayList<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @RequestMapping(value = "users/online")
    public @ResponseBody
    ArrayList<User> onlineUser(Authentication authentication) {
        ArrayList<User> users = new ArrayList<>();

        //User user = userRepository.findByUsername(authentication.getName()).get();
        for (User user : userRepository.findAll()) {
            if (System.currentTimeMillis() - user.getLastHeartbeat() < 15000) {
                if (user.getProject() == userRepository.findByUsername(authentication.getName()).get().getProject()) {
                    users.add(user);
                }
            }
        }
        return users;
    }

    @PostMapping(value = "/users")
    public @ResponseBody
    ArrayList<User> allUsers(@RequestParam(value = "team_id") int team_id){
        ArrayList<User> users = new ArrayList<>();
        if (team_id>0){
            users = userRepository.findAllByTeam(teamRepository.findOne(team_id));
        }else {
            userRepository.findAll().forEach(users::add);
        }
        return users;
    }

    @RequestMapping(value = "/codes")
    public @ResponseBody
    ArrayList<Code> allCodes(@RequestParam("project_id") int project_id){
        ArrayList<Code> codes = new ArrayList<>();
        if(project_id>0){
            codes = codeRepository.findAllByProject(projectRepository.findOne(project_id));
        }else{
            codeRepository.findAll().forEach(codes::add);
        }
        return codes;
    }


    @RequestMapping(value = "/project/all")
    public @ResponseBody
    Set<Project> projects(Authentication authentication){

        Team team = userRepository.findByUsername(authentication.getName()).get().getTeam();

        Set<Project> projects = team.getProjects();

        for(Project project:projects){

            int online = 0;
            for(User user : userRepository.findAll()){
                if(user.getProject() == project && System.currentTimeMillis() - user.getLastHeartbeat() < 15000){
                    online++;
                } else {
                    if (user == project.getMaintainer()) {
                        project.setMaintainer(null);
                    }
                }
            }
            project.setOnline(online);
            projectRepository.save(project);
        }
        return projects;
    }

    @RequestMapping(value = "/code/new")
    public @ResponseBody
    Code codeNew(@RequestParam("code_title") String code_title, Authentication authentication){
        User user = userRepository.findByUsername(authentication.getName()).get();

        String mode = code_title.split("\\.")[1];

//        Code code = new Code(code_title,"",mode,user.getProject(),user.getProject().getCodes().isEmpty());
        Code code = new Code(code_title, "", mode, user.getProject(), false);
        codeRepository.save(code);
        return code;
    }

    @GetMapping(value="/user")
    public @ResponseBody
    User getUserInfo(Authentication authentication){
        User user = userRepository.findByUsername(authentication.getName()).get();
        user.setLastHeartbeat(System.currentTimeMillis());
        userRepository.save(user);
        return user;
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
        return user;
    }

    @RequestMapping(value = "/project/current")
    public @ResponseBody
    long cproject(Authentication authentication){
        return userRepository.findByUsername(authentication.getName()).get().getProject().getProject_id();
    }

    @RequestMapping(value = "/teams")
    public @ResponseBody
    ArrayList<Team> allTeams(){
        ArrayList<Team> teams = new ArrayList<>();
        teamRepository.findAll().forEach(teams::add);
        return teams;
    }
    @PostMapping(value = "/project/take")
    public @ResponseBody
    Project takeProject(Authentication authentication){
        User user = userRepository.findByUsername(authentication.getName()).get();
        user.setLastHeartbeat(System.currentTimeMillis());
        userRepository.save(user);
        Project project = user.getProject();
        //有人管理
        if (project.getMaintainer() != null) {
            if (System.currentTimeMillis() - project.getMaintainer().getLastHeartbeat()>30000 | project.getMaintainer()==user){
                project.setMaintainer(null);
                projectRepository.save(project);
            }
        }else{
            //无人管理 直接接管
            project.setMaintainer(user);
            projectRepository.save(project);
        }
        return project;
    }
}