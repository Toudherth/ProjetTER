package com.projet.hello.repository;

import org.springframework.stereotype.Repository;

import com.projet.hello.entity.Offre;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface OffreRepository extends MongoRepository<Offre, String> {
    // Offre findByIdOffre(String idoffre);

    Offre findByNomoffre(String nomoffre);

}
