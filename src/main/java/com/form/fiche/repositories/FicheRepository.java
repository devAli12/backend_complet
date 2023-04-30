package com.form.fiche.repositories;

import com.form.fiche.entities.Fiche;
import com.form.fiche.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FicheRepository extends JpaRepository<Fiche,Long> {



    @Query("SELECT f FROM Fiche f LEFT JOIN FETCH f.ficheGoutteurs LEFT JOIN FETCH f.ficheMaraichage " +
        "LEFT JOIN FETCH f.ficheArboriculture LEFT JOIN FETCH f.ficheRessourcesEnEau LEFT JOIN FETCH f.irrigationConfiguration "+
        "LEFT JOIN FETCH f.ficheDocuments WHERE f.commercial = :commercial")
    List<Fiche> findByCommercial(@Param("commercial") String commercial);

    @Modifying
    @Query("SELECT f FROM Fiche f LEFT JOIN FETCH f.ficheGoutteurs LEFT JOIN FETCH f.ficheMaraichage " +
            "LEFT JOIN FETCH f.ficheArboriculture LEFT JOIN FETCH f.ficheRessourcesEnEau LEFT JOIN FETCH f.irrigationConfiguration "+
            "LEFT JOIN FETCH f.ficheDocuments WHERE f.user = :user")
    List<Fiche> getAllFichesByUser(User user);
}
