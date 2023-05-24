package com.inter.controllers;

import com.inter.entity.Abonnement;
import com.inter.entity.Agence;
import com.inter.repositories.AgenceRepository;
import com.inter.repositories.AppRolesRepository;
import com.inter.repositories.AppUserRepository;
import com.inter.service.AgenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agences")
public class AgenceController {
    private AgenceRepository agenceRepository;
    private AgenceService agenceService;
    private PasswordEncoder passwordEncoder;
    private AppRolesRepository rolesRepository;
    private AppUserRepository userRepository;

    // conctructeur d'injection de dependences
    @Autowired
    public AgenceController(AgenceService agenceService, AgenceRepository agenceRepository,
            PasswordEncoder passwordEncoder, AppRolesRepository rolesRepository, AppUserRepository userRepository) {
        this.agenceService = agenceService;
        this.agenceRepository = agenceRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
        this.userRepository = userRepository;
    }

    // Inscrire avec un type abonnement
    @PostMapping("/inscription")
    public Agence inscrireAgenceAvecFormule(@RequestParam String nom,
            @RequestParam String metier,
            @RequestParam Long periode,
            @RequestParam String renumeration,
            @RequestParam String description,
            @RequestParam String formuleAbonnementId,
            @RequestParam String username,
            @RequestParam String password) {
        return agenceService.inscrireAgenceAvecFormule(nom, metier, periode,
                renumeration, description, formuleAbonnementId, username, password);
    }
    // @PostMapping("/inscription")
    // public Agence inscrireAgence(String nom, @RequestParam String metier,
    // @RequestParam Long periode,
    // @RequestParam String renumeration,
    // @RequestParam String description, @RequestParam String formuleAbonnementId) {
    // return agenceService.inscrireAgence(nom, metier, periode, renumeration,
    // description, formuleAbonnementId);
    // }

    // inscription sans type d'abonnement
    @GetMapping
    public List<Agence> getAll() {
        return agenceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Agence getById(@PathVariable("id") String id) {
        return (Agence) agenceRepository.findById(id).orElse(null);
    }

    @PostMapping
    @Transactional
    public Agence addAgence(@RequestBody Agence agence) {
        return agenceRepository.save(agence);
    }

    @PutMapping("/{id}")
    @Transactional
    public Agence update(@PathVariable("id") String id, @RequestBody Agence updatedAgence) {
        Agence agence = (Agence) agenceRepository.findById(id).orElse(null);
        if (agence != null) {
            agence.setNomAgence(updatedAgence.getNomAgence());
            agence.setMetier(updatedAgence.getMetier());
            agence.setPeriode(updatedAgence.getPeriode());
            agence.setRenumeration(updatedAgence.getRenumeration());
            agence.setDescription(updatedAgence.getDescription());
            return agenceRepository.save(agence);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable("id") String id) {
        Agence agence = (Agence) agenceRepository.findById(id).orElse(null);
        if (agence != null) {
            agenceRepository.delete(agence);
        }
    }

}