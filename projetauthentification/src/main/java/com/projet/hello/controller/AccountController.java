package com.projet.hello.controller;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.hello.entity.AppRoles;
import com.projet.hello.entity.AppUser;
import com.projet.hello.service.AccountService;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/hello")
    public String Hello() {
        return "Hello world";
    }

    @GetMapping
    @PostAuthorize("hasAuthority('admin')")
    @RequestMapping("/users")
    public List<AppUser> AllUsers() {
        return accountService.AllUsers();
    }

    @PostMapping
    @PostAuthorize("hasAuthority('admin')")
    @RequestMapping("/add-users")
    public AppUser addAppUser(@RequestBody AppUser user) {
        return accountService.addAppUser(user);
    }

    @PostMapping
    @PostAuthorize("hasAuthority('admin')")
    @RequestMapping("/roles")
    public AppRoles addAppRole(@RequestBody AppRoles role) {
        return accountService.addAppRole(role);
    }

    @GetMapping
    @RequestMapping("/users/{username}")
    public AppUser loadUserByUsername(@PathVariable String username) {
        return accountService.loadUserByUsername(username);
    }

    @GetMapping
    @RequestMapping("/roles/{rolename}")
    public AppRoles loadUserByRolename(@PathVariable String rolename) {
        return accountService.loadUserByRolename(rolename);
    }

    @PutMapping
    @RequestMapping("/users/{username}/{rolename}")
    public void addRoleToUser(@PathVariable String username, @PathVariable String rolename) {
        accountService.addRoleToUser(username, rolename);
    }

    @GetMapping("/profile")
    public AppUser profile(Principal principal) {
        return accountService.loadUserByUsername(principal.getName());
    }

    @GetMapping(path = "/refreshtoken")
    public void refreshtoken(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String authtoken = request.getHeader("Authorization");
        if (authtoken != null && authtoken.startsWith("Bearer")) {

            try {
                String jwt = authtoken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256("mySecret1234");

                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String username = decodedJWT.getSubject();

                AppUser appUser = accountService.loadUserByUsername(username);
                String jwtAccessToken = JWT.create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("roles",
                                appUser.getRoles().stream().map(r -> r.getRolename()).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> idToken = new HashMap<>();
                idToken.put("access-token", jwtAccessToken);
                idToken.put("refresh-token", jwt);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);

            } catch (Exception e) {

                response.setHeader("error-message", e.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            throw new RuntimeException("Refresh token required !");
        }
    }

}
