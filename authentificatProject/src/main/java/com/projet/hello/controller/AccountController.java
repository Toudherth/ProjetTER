package com.projet.hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.hello.entity.AppRole;
import com.projet.hello.entity.AppUser;
import com.projet.hello.repository.AppRoleRepository;
import com.projet.hello.repository.AppUserRepository;

@RestController
public class AccountController {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;

    @RequestMapping("/hello")
    public String Hello() {
        return "Hello world";
    }

    @GetMapping
    @RequestMapping("/users")
    public List<AppUser> AllUsers() {
        return appUserRepository.findAll();
    }

    @PostMapping
    @RequestMapping("/add-users")
    public AppUser addAppUser(@RequestBody AppUser user) {
        return appUserRepository.save(user);
    }

    @PostMapping
    @RequestMapping("/roles")
    public AppRole addAppRole(@RequestBody AppRole role) {
        return appRoleRepository.save(role);
    }

    @GetMapping
    @RequestMapping("/users/{username}")
    public AppUser loadUserByUsername(@PathVariable String username) {
        return appUserRepository.findByUsername(username);
    }

    @PutMapping
    @RequestMapping("/users/{username}/{rolename}")
    public void addRoleToUser(@PathVariable String username, @PathVariable String rolename) {
        AppUser user = appUserRepository.findByUsername(username);
        AppRole role = appRoleRepository.findByRolename(rolename);

        user.getAppRoles().add(role);
    }

}
