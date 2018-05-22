package com.lixinyu.cooperativecoding.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIgnoreProperties(value = {"project"})
public class User {

    @Id
    private String username;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    private String name;

    private String password;

//    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name="uid",referencedColumnName = "username"),inverseJoinColumns = @JoinColumn(name = "role",referencedColumnName = "role_id"))
//    private Set<Role> roles;
    private String role;


    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private long lastHeartbeat=0;
    private boolean active;
    public User(){
    }

    public User(User user){
        this.username = user.getUsername();
        this.team = user.getTeam();
        this.name = user.getName();
        this.password = user.getPassword();
//        this.roles = user.getRoles();
        this.role = user.getRole();
        this.active = user.getActive();
    }

    public User(String username, Team team, String name, String password, String role, Project project, boolean active) {
        this.username = username;
        this.team = team;
        this.name = name;
        this.password = password;
//        this.roles = roles;
        this.role = role;
        this.project = project;
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Set<Role> getRoles() {
//        return roles;
//    }

//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }

//    public void addRole(Role role) {
//        this.roles.add(role);
//    }

//    public void removeRole(Role role) {
//        this.roles.remove(role);
//    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public long getLastHeartbeat() {
        return lastHeartbeat;
    }

    public void setLastHeartbeat(long lastHeartbeat) {
        this.lastHeartbeat = lastHeartbeat;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}