package com.form.fiche.repositories;

import com.form.fiche.entities.Fiche;
import com.form.fiche.entities.FicheDocuments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FicheDocumentsRepository extends JpaRepository<FicheDocuments,Long> {


}
