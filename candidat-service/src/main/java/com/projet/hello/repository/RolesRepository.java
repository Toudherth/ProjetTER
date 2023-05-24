package com.projet.hello.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.projet.hello.entity.AppRoles;

@Repository
public interface RolesRepository extends MongoRepository<AppRoles, String> {

    AppRoles findByRolename(String rolename);
}
