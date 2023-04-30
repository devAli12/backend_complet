package com.form.fiche.repositories;

import com.form.fiche.entities.BorneIrrigation;
import com.form.fiche.entities.FicheRessourcesEnEau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorneIrrigationRepository extends JpaRepository<BorneIrrigation,Long> {


    List<BorneIrrigation> findByFicheRessourcesEnEau(FicheRessourcesEnEau ficheRessourcesEnEau);
}
