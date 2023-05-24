package com.inter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.inter.entity.Abonnement;
import com.inter.repositories.AbonnementRepository;
import com.inter.service.AgenceService;

@RestController
@RequestMapping("/abonnements")
public class AbonnementController {

    @Autowired
    private AbonnementRepository abonnementRepository;
    @Autowired
    private AgenceService agenceService;

    // recuperer la liste des abonnement
    @GetMapping()
    public List<Abonnement> getAllAbonnements() {
        return agenceService.getAllAbonnements();
    }

    @PostMapping
    @Transactional
    public Abonnement addAbonnement(@RequestBody Abonnement agence) {
        return abonnementRepository.save(agence);
    }

    @GetMapping("/{id}")
    public Abonnement getabonnementById(@PathVariable("id") String id) {
        return (Abonnement) abonnementRepository.findById(id).orElse(null);
    }

}
