package com.lixinyu.cooperativecoding.Entries;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Code {
    @Id
	private int team;
    private String name;
	private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Code(int team, String name, String content) {

        this.team = team;
        this.name = name;
        this.content = content;
    }

    public int getTeam() {
		return team;
	}

	public void setTeam(int id) {
		this.team = team;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
