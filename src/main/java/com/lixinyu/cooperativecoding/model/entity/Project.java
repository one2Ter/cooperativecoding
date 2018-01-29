package com.lixinyu.cooperativecoding.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIgnoreProperties(value = {"codes"})
public class Project {
    @Id

    private int project_id;

    //项目名称
    private String project_name;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Code> codes;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Project() {
    }

    public long getProject_id() {
        return project_id;
    }

    public Project(int project_id, String project_name, Team team) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.team = team;
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

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }
}