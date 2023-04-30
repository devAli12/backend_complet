package com.form.fiche.repositories;

import com.form.fiche.entities.FicheRessourcesEnEau;
import com.form.fiche.entities.Forage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForageRepository extends JpaRepository<Forage,Long> {
    List<Forage> findByFicheRessourcesEnEau(FicheRessourcesEnEau ficheRessourcesEnEau);
}
