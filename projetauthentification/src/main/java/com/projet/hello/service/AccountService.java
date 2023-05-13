package com.projet.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projet.hello.entity.AppRoles;
import com.projet.hello.entity.AppUser;
import com.projet.hello.repository.AppRoleRepository;
import com.projet.hello.repository.AppUserRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AppRoleRepository appRoleRepository;
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<AppUser> AllUsers() {
        return appUserRepository.findAll();
    }

    public AppUser addAppUser(AppUser user) {

        String pw = user.getPassword();
        user.setPassword(passwordEncoder.encode(pw));
        return appUserRepository.save(user);
    }

    public AppRoles addAppRole(AppRoles role) {
        return appRoleRepository.save(role);
    }

    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public AppRoles loadUserByRolename(String rolename) {
        return appRoleRepository.findByRolename(rolename);
    }

    public void addRoleToUser(String username, String rolename) {

        AppUser user = appUserRepository.findByUsername(username);
        AppRoles role = appRoleRepository.findByRolename(rolename);

        user.getRoles().add(role);
    }

}
