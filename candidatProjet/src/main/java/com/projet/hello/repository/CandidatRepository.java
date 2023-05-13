package com.projet.hello.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.projet.hello.entity.Candidats;

@Repository
public interface CandidatRepository extends MongoRepository<Candidats, String> {

    Candidats findByNomcandidat(String nomcandidat);

}
