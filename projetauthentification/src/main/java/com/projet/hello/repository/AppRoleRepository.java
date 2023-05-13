package com.projet.hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.hello.entity.AppRoles;

//@Repository
public interface AppRoleRepository extends JpaRepository<AppRoles, Long> {

    AppRoles findByRolename(String rolename);

}
