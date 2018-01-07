package com.lixinyu.cooperativecoding.Entries;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private int id;
    private int team;
    private String name;
    private int maintainer;

    public User(){
    }

    public User(int id,int team, String name, int maintainer) {
        this.id = id;
        this.team = team;
        this.name = name;
        this.maintainer = maintainer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int group) {
        this.team = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(int privilege) {
        this.maintainer = privilege;
    }
}
