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
@Document(collection = "offres")
public class Offre {

    @Id
    private String idoffre;
    private String nomoffre;
    private String metier;
    private String desciption;
    private String ville;
    private String periode;
    private String salaire;
    @DBRef
    private List<Candidats> candidats = new ArrayList<>();

    @Override
    public String toString() {
        return "Offre [idoffre=" + idoffre + ", nomoffre=" + nomoffre + ", metier=" + metier + ", desciption="
                + desciption + ", ville=" + ville + ", periode=" + periode + ", salaire=" + salaire + "]";
    }

}
