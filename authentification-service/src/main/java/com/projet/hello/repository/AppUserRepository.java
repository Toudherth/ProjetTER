package com.projet.hello.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.projet.hello.entity.AppUser;

@Repository
public interface AppUserRepository extends MongoRepository<AppUser, Long> {

    AppUser findByUsername(String username);

}
