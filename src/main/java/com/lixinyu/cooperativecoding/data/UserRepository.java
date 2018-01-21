package com.lixinyu.cooperativecoding.data;

import com.lixinyu.cooperativecoding.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {

    Optional<User> findByUsername(String username);
}
