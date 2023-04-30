package com.form.fiche.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IrrigationConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filtration;
    private String commandeDesVannes;
    private String  fertigation;
    private String agitationDesEngrais;
    private String pompeDeReprise;
    private String demarragePompeDeReprise;
    private String pompeDeRemplissage;
    private String demarragePompeDeRemplissage;
    private String nombreEtVolumeDesCiternes;
    private String observation;

}
