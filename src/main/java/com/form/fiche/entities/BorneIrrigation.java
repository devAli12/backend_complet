package com.form.fiche.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorneIrrigation extends Ressource {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    Long id;


    private Double debitSurPression;

    @Embedded
    private Coordonnee coordonnee;

    @Override
    public void setDebitSurPression(Double debitSurPression) {
        this.debitSurPression = debitSurPression;
    }

    @Override
    public void setFicheRessourcesEnEau(FicheRessourcesEnEau savedFicheRessourcesEnEau) {
        this.ficheRessourcesEnEau = savedFicheRessourcesEnEau;
    }

    @Override
    public void setCoordonnee(Coordonnee coordonnee) {
        this.coordonnee = coordonnee;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private FicheRessourcesEnEau ficheRessourcesEnEau;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BorneIrrigation borneIrrigation)) return false;
        return Objects.equals(id, borneIrrigation.id);
    }

}
