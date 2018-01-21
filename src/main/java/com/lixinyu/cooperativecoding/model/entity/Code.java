package com.lixinyu.cooperativecoding.model.entity;

import javax.persistence.*;

@Entity
public class Code {
    @Id
	private int code_id;
    private String name;
	private String content;
	private String type;

	@ManyToOne
    @JoinColumn(name = "project_id")

	private Project project;

    public Code() {

    }

    public Code(int code_id, String name, String content, String type, Project project) {
        this.code_id = code_id;
        this.name = name;
        this.content = content;
        this.type = type;
        this.project = project;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getCode_id() {
		return code_id;
	}

	public void setCode_id(int id) {
		this.code_id = code_id;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}