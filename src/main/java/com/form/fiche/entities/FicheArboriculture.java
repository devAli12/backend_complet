package com.form.fiche.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FicheArboriculture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String arboricultureCulture;
    private Double distanceEntreLignesDePlantation;
    private Double distanceEntreLesArabres;
    private Integer arboricultureNbreDeRempesParLigne;
    private String sensDePlanatationSupl;
    private String autres;
    private String nbreDeGoutteursParArabre;


}
