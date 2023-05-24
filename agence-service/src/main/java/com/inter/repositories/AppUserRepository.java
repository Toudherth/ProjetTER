package com.inter.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.inter.entity.AppUser;

@Repository
public interface AppUserRepository extends MongoRepository<AppUser, String> {

    AppUser findByUsername(String username);
}