package com.form.fiche.repositories;

import com.form.fiche.entities.Fiche;
import com.form.fiche.entities.FicheGoutteurs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FicheGoutteursRepository extends JpaRepository<FicheGoutteurs,Long> {


}
