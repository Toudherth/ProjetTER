package com.inter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Abonnement {
    @Id
    private String idabonnement;
    private String nomAbonnement;
    private String dateDebut;
    private String dateFin;
    private String type;
    private float prix;
    private String description;
}