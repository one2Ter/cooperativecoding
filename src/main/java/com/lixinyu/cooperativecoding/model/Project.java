package com.lixinyu.cooperativecoding.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //项目id
    private int id;
    //项目名称
    private String name;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "project_file",joinColumns = @JoinColumn(name="project_id"),inverseJoinColumns = @JoinColumn(name = "file_id"))
    private Set<Code> codes;
}