package com.form.fiche.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FicheDocuments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean cinLegalisee;

    private Boolean devis;
    private Boolean engagementFournisseur;
    private Boolean portant;

    private Boolean demandeExamen;

    private Boolean contratBail;
    private Boolean titreFoncier;
    private Boolean certificatPropriete;
    private Boolean acteLegalise;
    private Boolean planBornage;
    private Boolean autorisationPuits;

    private String planBornageFileName;

    private String  cinLegaliseeFileName;

    private String  devisFileName;
    private String engagementFournisseurFileName;
    private String portantFileName;

    private String demandeExamenFileName;

    private String contratBailFileName;
    private String titreFoncierFileName;
    private String certificatProprieteFileName;
    private String acteLegaliseFileName;
    private String autorisationPuitsFileName;

}
