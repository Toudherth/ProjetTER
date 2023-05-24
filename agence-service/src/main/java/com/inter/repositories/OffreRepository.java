package com.inter.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.inter.entity.Offre;

import java.util.Optional;

@Repository
public interface OffreRepository extends MongoRepository<Offre, Long> {
    Optional<Offre> findById(Long id);

}
