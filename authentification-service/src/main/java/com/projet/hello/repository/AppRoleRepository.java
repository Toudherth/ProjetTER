package com.projet.hello.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.projet.hello.entity.AppRoles;

@Repository
public interface AppRoleRepository extends MongoRepository<AppRoles, Long> {

    AppRoles findByRolename(String rolename);

}
