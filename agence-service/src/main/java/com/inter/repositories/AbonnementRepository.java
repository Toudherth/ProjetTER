package com.inter.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.inter.entity.Abonnement;

@Repository
public interface AbonnementRepository extends MongoRepository<Abonnement, String> {
}
