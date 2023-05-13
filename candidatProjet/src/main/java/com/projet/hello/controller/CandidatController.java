package com.projet.hello.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.hello.entity.Candidats;
import com.projet.hello.repository.CandidatRepository;

import jakarta.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidats")
public class CandidatController {

    @Autowired
    private CandidatRepository candidatrepository;

    @RequestMapping("/hello")
    public String Hello() {
        return "Hello world";
    }

    @GetMapping
    public List<Candidats> allCandidats() {
        return candidatrepository.findAll();
    }

    @PostMapping
    @RequestMapping("/candidat")
    public Candidats addCandidat(@RequestBody Candidats candidat) {
        return candidatrepository.save(candidat);
    }

    // recherche par id

    @GetMapping
    @RequestMapping("/{nomcandidat}")
    public Candidats getCandidat(@PathParam("nomcandidat") String nomcandidat) {
        return candidatrepository.findByNomcandidat(nomcandidat);
    }

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
