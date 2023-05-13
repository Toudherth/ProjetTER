package com.projet.hello.entity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    private String id;
    private String username;
    private String password;

    @DBRef
    private Collection<AppRole> appRoles = new ArrayList<>();

}
