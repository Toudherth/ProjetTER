package com.inter.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.inter.entity.AppRoles;

@Repository
public interface AppRolesRepository extends MongoRepository<AppRoles, String> {

    AppRoles findByRolename(String rolename);
}
