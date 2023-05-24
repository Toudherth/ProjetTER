package com.projet.hello.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.hello.entity.AppRoles;
import com.projet.hello.entity.AppUser;
import com.projet.hello.entity.Candidats;
import com.projet.hello.repository.CandidatRepository;
import com.projet.hello.repository.RolesRepository;
import com.projet.hello.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/candidats")
public class CandidatController {
    @Autowired
    private CandidatRepository candidatrepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Ajouter candidat avec user et role { s'inscrire }
    @PostMapping
    @RequestMapping("/inscrire")
    @Transactional
    public Candidats addCandidatsUser(@PathParam("username") String username, @PathParam("password") String password,
            @RequestBody Candidats candidat) {
        String pw = password;
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(pw));
      
        AppRoles candidatRole = rolesRepository.findByRolename("candidat");
        List<AppRoles> roles = new ArrayList<>();
        roles.add(candidatRole);
        user.setRoles(roles);
        
        userRepository.save(user);
        candidat.setUser(user);
        return candidatrepository.save(candidat);
    }
    // get all candidats
    @GetMapping
    public List<Candidats> allCandidats() {
        return candidatrepository.findAll();
    }
    // recherche par id
    @GetMapping
    @RequestMapping("/{nomcandidat}")
    public Candidats getCandidat(@PathParam("nomcandidat") String nomcandidat) {
        return candidatrepository.findByNomcandidat(nomcandidat);
    }

    // Modifier information candidat
    @PutMapping
    @Transactional
    @RequestMapping("/{id}")
    public void updateCandidat(String id, Candidats candidat) {
        Optional<Candidats> optionalCandidat = candidatrepository.findById(id);
        if (optionalCandidat.isPresent()) {
            Candidats existingCandidat = optionalCandidat.get();
            existingCandidat.setNomcandidat(candidat.getNomcandidat());
            existingCandidat.setPrenomcandidat(candidat.getPrenomcandidat());
            existingCandidat.setDatenaissance(candidat.getDatenaissance());
            existingCandidat.setNationalite(candidat.getNationalite());
            existingCandidat.setNumerotel(candidat.getNumerotel());
            existingCandidat.setCv(candidat.getCv());
            existingCandidat.setLettremotivation(candidat.getLettremotivation());

            // mettre à jour d'autres propriétés si nécessaire
            candidatrepository.save(existingCandidat);
        }
    }

    // delete candidat
    @DeleteMapping("/{id}")
    @Transactional
    public void deleteCandidat(String id) {
        Candidats candidat = candidatrepository.findById(id).orElse(null);
        candidatrepository.delete(candidat);
    }

    /// pour le get way et rest client
    @GetMapping
    @RequestMapping("/gatewaycandidat")
    public String getOffre() {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/microservice1/candidats"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);

            return root.get(0).get("nationalite").toString();

        } catch (IOException | InterruptedException e) {
            // handle exception
        }
        return null;
    }

}
