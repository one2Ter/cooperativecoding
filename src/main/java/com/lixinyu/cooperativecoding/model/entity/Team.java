package com.lixinyu.cooperativecoding.model.entity;

import com.lixinyu.cooperativecoding.model.entity.Project;
import com.lixinyu.cooperativecoding.model.entity.User;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Team {
    @Id
    private int team_id;
    private String team_name;
    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL)
    private Set<User> users;

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL)
    private Set<Project> projects;
}
