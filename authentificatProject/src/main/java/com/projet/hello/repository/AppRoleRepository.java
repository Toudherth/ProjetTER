package com.projet.hello.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.projet.hello.entity.AppRole;

@Repository
public interface AppRoleRepository extends MongoRepository<AppRole, String> {

    AppRole findByRolename(String rolename);

}
