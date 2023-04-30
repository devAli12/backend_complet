package com.form.fiche.repositories;

import com.form.fiche.entities.FicheRessourcesEnEau;
import com.form.fiche.entities.Puit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PuitRepository extends JpaRepository<Puit,Long> {
    List<Puit> findByFicheRessourcesEnEau(FicheRessourcesEnEau ficheRessourcesEnEau);
}
