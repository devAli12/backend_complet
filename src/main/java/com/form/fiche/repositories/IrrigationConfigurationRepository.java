package com.form.fiche.repositories;

import com.form.fiche.entities.Fiche;
import com.form.fiche.entities.IrrigationConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IrrigationConfigurationRepository extends JpaRepository<IrrigationConfiguration,Long> {

}
