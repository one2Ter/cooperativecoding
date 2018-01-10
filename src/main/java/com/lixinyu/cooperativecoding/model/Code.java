package com.lixinyu.cooperativecoding.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Code {
    @Id
	private int team;
    private String name;
	private String content;
	private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Code(int team, String name, String content,String type) {

        this.team = team;
        this.name = name;
        this.content = content;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
