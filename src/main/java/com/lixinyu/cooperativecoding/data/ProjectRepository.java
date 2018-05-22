package com.lixinyu.cooperativecoding.data;

import com.lixinyu.cooperativecoding.model.entity.Project;
import com.lixinyu.cooperativecoding.model.entity.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ProjectRepository extends CrudRepository<Project,Integer> {


    ArrayList<Project> findAllByTeam(Team team);
}
