package com.projet.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.hello.entity.Candidats;
import com.projet.hello.entity.Offre;
import com.projet.hello.repository.CandidatRepository;
import com.projet.hello.repository.OffreRepository;

@Service
public class OffreCandidatService {

    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    private OffreRepository offreRepository;

    public Offre createOrUpdateOffre(String idoffre, String nomoffre, String idcandidat) {
        Offre offre;
        if (idoffre == null) {
            offre = new Offre();
        } else {
            offre = offreRepository.findById(idoffre).orElse(null);
            // offre = offreRepository.findByIdOffre(idoffre);
        }
        offre.setNomoffre(nomoffre);
        Candidats candidat = candidatRepository.findById(idcandidat).orElse(null);
        // offre.getCandidats().add(candidat);
        candidat.getOffres().add(offre);
        offreRepository.save(offre);
        candidatRepository.save(candidat);
        return offre;
    }

}
