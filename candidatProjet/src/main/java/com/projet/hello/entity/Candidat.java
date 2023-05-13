package com.projet.hello.entity;

import java.io.File;
import java.awt.Image;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Candidat {
    @Id
    private String idcandidat;
    private String nomcandidat;
    private String prenomcandidat;
    private String nationalite;
    private String datenaissance;
    private String numerotel;
    private String cv;
    private String lettremotivation;

    /*
     * private File cv;
     * private File lettremotivation;
     * private Image image;
     */

}
