package com.lixinyu.cooperativecoding.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@JsonIgnoreProperties(value = {"project"})
public class Code {
    @Id
    private int code_id;
    private String code_title;
	private String content;
	private String type;

	@ManyToOne
    @JoinColumn(name = "project_id")
	private Project project;

    private boolean main;

    public Code() {

    }

    public Code(int code_id, String code_title, String content, String type, Project project) {
        this.code_id = code_id;
        this.code_title = code_title;
        this.content = content;
        this.type = type;
        this.project = project;
    }

    public String getCode_title() {
        return code_title;
    }

    public void setCode_title(String code_title) {
        this.code_title = code_title;
    }

    public long getCode_id() {
		return code_id;
	}

    public void setCode_id(int code_id) {
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