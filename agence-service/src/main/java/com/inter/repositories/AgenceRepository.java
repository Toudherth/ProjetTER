package com.inter.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.inter.entity.Agence;

@Repository
public interface AgenceRepository extends MongoRepository<Agence, String> {

}

// Implémentez vos méthodes spécifiques pour la manipulation des entités Agence