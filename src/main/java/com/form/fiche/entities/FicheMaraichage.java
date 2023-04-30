package com.form.fiche.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FicheMaraichage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String maraichage;
    private String culture;
    private Double distanceEntreLignes;
    private Double distanceEntrePlante;
    private Integer nbreDeRempesParLigne;
    private String sensDePlantation;

}
