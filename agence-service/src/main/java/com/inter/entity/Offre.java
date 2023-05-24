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
public class Offre {
    @Id
    private Long id;
    private String nom_offre;
    private String metier_offre;
    private Date date_offre;
    private String description;
    private String periode;
    private String renumeration;

    @DBRef
    private Agence agence;
}