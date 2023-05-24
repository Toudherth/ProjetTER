package com.inter.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inter.service.AgenceService;

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

@RestController
@RequestMapping("agence/offres")
public class OffreController {

    private AgenceService agenceService;

    // injection de dependences
    public OffreController(AgenceService agenceService) {
        this.agenceService = agenceService;
    }
    // ajouter offre

    /*
     * @GetMapping
     * 
     * @RequestMapping("/gatewaycandidat")
     * public String getOffre() {
     * 
     * HttpClient client = HttpClient.newHttpClient();
     * HttpRequest request = HttpRequest.newBuilder()
     * .uri(URI.create("http://localhost:8080/microservice1/candidats"))
     * .GET()
     * .build();
     * try {
     * HttpResponse<String> response = client.send(request,
     * HttpResponse.BodyHandlers.ofString());
     * String responseBody = response.body();
     * ObjectMapper mapper = new ObjectMapper();
     * JsonNode root = mapper.readTree(responseBody);
     * 
     * return root.get(0).get("nationalite").toString();
     * 
     * } catch (IOException | InterruptedException e) {
     * // handle exception
     * 
     * }
     * }
     * 
     */
}
