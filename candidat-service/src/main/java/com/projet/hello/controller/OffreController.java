package com.projet.hello.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.PermitAll;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.hello.entity.Offre;
import com.projet.hello.repository.OffreRepository;
import com.projet.hello.service.OffreCandidatService;

@RestController
@RequestMapping("/offres")
public class OffreController {
    @Autowired
    private OffreRepository offreRepository;
    @Autowired
    private OffreCandidatService offreCandidatService;

    // postuler Offre
    @PostMapping
    @RequestMapping("/postuler")
    public Offre createOrUpdateBook(@PathParam("idoffre") String idoffre,
            @PathParam("nomoffre") String nomoffre,
            @PathParam("idcandidat") String idcandidat) {
        return offreCandidatService.createOrUpdateOffre(idoffre, nomoffre, idcandidat);
    }

    // Afficher offre
    @GetMapping
    public List<Offre> allOffres() {
        return offreRepository.findAll();
    }

    // Ajouter offre
    @PostMapping
    public Offre addOffre(@RequestBody Offre offre) {
        return offreRepository.save(offre);
    }

    // recherche par nom
    @GetMapping
    @RequestMapping("/{nomoffre}")
    public Offre getCandidat(@PathParam("nomoffre") String nomoffre) {
        return offreRepository.findByNomoffre(nomoffre);
    }

    // Modifier Offre
    @PutMapping("/{id}")
    @Transactional
    public void updateOffre(String id, Offre offre) {
        Optional<Offre> optionalOffre = offreRepository.findById(id);
        if (optionalOffre.isPresent()) {
            Offre existingOffre = optionalOffre.get();
            existingOffre.setNomoffre(offre.getNomoffre());
            existingOffre.setMetier(offre.getMetier());
            existingOffre.setDesciption(offre.getDesciption());
            existingOffre.setPeriode(offre.getPeriode());
            existingOffre.setSalaire(offre.getSalaire());
            existingOffre.setVille(offre.getVille());
            // mettre à jour d'autres propriétés si nécessaire
            offreRepository.save(existingOffre);
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    @Transactional
    public void deleteOffre(String id) {
        Offre offre = offreRepository.findById(id).orElse(null);
        // .orElseThrow(() -> new EntityNotFoundException("Candidat with id " + id + "
        // not found"));
        offreRepository.delete(offre);
    }

}
