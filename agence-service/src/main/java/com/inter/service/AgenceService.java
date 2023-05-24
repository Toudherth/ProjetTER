package com.inter.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inter.entity.Abonnement;
import com.inter.entity.Agence;
import com.inter.entity.AppRoles;
import com.inter.entity.AppUser;
import com.inter.repositories.AbonnementRepository;
import com.inter.repositories.AgenceRepository;
import com.inter.repositories.AppRolesRepository;
import com.inter.repositories.AppUserRepository;

@Service
public class AgenceService {

    private PasswordEncoder passwordEncoder;
    private AppRolesRepository rolesRepository;
    private AppUserRepository userRepository;
    private AbonnementRepository abonnementRepository;
    private AgenceRepository agenceRepository;

    @Autowired
    public AgenceService(AbonnementRepository abonnementRepository, AgenceRepository agenceRepository,
            PasswordEncoder passwordEncoder, AppRolesRepository rolesRepository, AppUserRepository userRepository) {
        this.abonnementRepository = abonnementRepository;
        this.agenceRepository = agenceRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
        this.userRepository = userRepository;
    }

    public List<Abonnement> getAllAbonnements() {
        return abonnementRepository.findAll();
    }

    public Agence inscrireAgence(String nom, String metier, Long periode, String renumeration,
            String description, String formuleAbonnementId) {

        // ObjectId objectId = new ObjectId(formuleAbonnementId);
        Abonnement formuleAbonnement = abonnementRepository.findById(formuleAbonnementId)
                .orElseThrow(() -> new IllegalArgumentException("Formule d'abonnement non trouvée"));

        Agence agence = new Agence();
        agence.setNomAgence(nom);
        agence.setMetier(metier);
        agence.setPeriode(periode);
        agence.setRenumeration(renumeration);
        agence.setDescription(description);
        agence.setAbonnement(formuleAbonnement);
        return agenceRepository.save(agence);
    }

    public Agence inscrireAgenceAvecFormule(String nom, String metier, Long periode,
            String renumeration, String description, String formuleAbonnementId, String username, String password) {
        // Créer un nouvel utilisateur
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        Abonnement formuleAbonnement = abonnementRepository.findById(formuleAbonnementId)
                .orElseThrow(() -> new IllegalArgumentException("Formule d'abonnement non trouvée"));
        // Ajouter le rôle "agence" à l'utilisateur
        AppRoles agenceRole = rolesRepository.findByRolename("agence");
        List<AppRoles> roles = new ArrayList<>();
        roles.add(agenceRole);
        user.setRoles(roles);
        // Enregistrer l'utilisateur dans la base de données
        userRepository.save(user);
        // Créer une nouvelle instance d'agence
        Agence agence = new Agence();
        agence.setNomAgence(nom);
        agence.setMetier(metier);
        agence.setPeriode(periode);
        agence.setRenumeration(renumeration);
        agence.setDescription(description);
        agence.setAbonnement(formuleAbonnement);
        agence.setUser(user);
        // Enregistrer l'agence dans la base de données
        return agenceRepository.save(agence);
    }

}
