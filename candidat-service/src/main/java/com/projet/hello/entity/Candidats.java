package com.projet.hello.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
// @ToString
@Document
public class Candidats {
    @Id
    private String idcandidat;
    private String nomcandidat;
    private String prenomcandidat;
    private String nationalite;
    private String datenaissance;
    private String numerotel;
    private String cv;
    private String lettremotivation;

    @DBRef
    private AppUser user;

    @DBRef
    private List<Offre> offres = new ArrayList();

    @Override
    public String toString() {
        return "Candidats [idcandidat=" + idcandidat + ", nomcandidat=" + nomcandidat + ", prenomcandidat="
                + prenomcandidat + ", nationalite=" + nationalite + ", datenaissance=" + datenaissance + ", numerotel="
                + numerotel + ", cv=" + cv + ", lettremotivation=" + lettremotivation + "]";
    }

}
