package com.lixinyu.cooperativecoding.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //项目id
    private int project_id;

    //项目名称
    private String project_name;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL)
    private Set<Code> codes;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Project() {
    }

    public Project(String project_name, Set<Code> codes) {
        this.project_name = project_name;
        this.codes = codes;
    }
}