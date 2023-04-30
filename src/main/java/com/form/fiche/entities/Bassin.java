package com.form.fiche.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bassin {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;

    private Boolean existeBassin;
    private Double longueur;
    private Double largeur;
    private Double hauteur;
    private Boolean aproposer;
    private Double capacite;
    private Double dimension;

}
