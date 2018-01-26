package com.lixinyu.cooperativecoding.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long project_id;

    //项目名称
    private String project_name;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL)
    private Set<Code> codes;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Project() {
    }

    public long getProject_id() {
        return project_id;
    }

    public void setProject_id(long project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public Set<Code> getCodes() {
        return codes;
    }

    public void setCodes(Set<Code> codes) {
        this.codes = codes;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}