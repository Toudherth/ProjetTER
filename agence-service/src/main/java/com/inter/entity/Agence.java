package com.inter.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Agence {
    @Id
    private String idagence;
    private String nomAgence;
    private String description;
    private String metier;
    private Long periode;
    private String renumeration;
    @DBRef
    private AppUser user;
    @DBRef
    private List<Offre> offres = new ArrayList();
    @DBRef
    private Abonnement abonnement;
}