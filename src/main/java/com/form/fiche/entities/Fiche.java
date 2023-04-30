package com.form.fiche.entities;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fiche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFiche;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JsonIgnoreProperties("fiches")
    private User user;
    private Date dateFiche;
    private String domaine;
    private String situationGeographique;
    private String proprietaire;
    private String locataire;
    private Double superficieTotale;
    private Double superficieAEquiper;
    private String commercial;
    private String numDossier;
    private String phone;
    private String etude;
    private String installationPrincipale;
    private String installationPorteRampe;

    private Boolean saveInProgress;

    @OneToOne(cascade = CascadeType.ALL)
    private FicheGoutteurs ficheGoutteurs;

    @OneToOne(cascade = CascadeType.ALL)
    private FicheMaraichage ficheMaraichage;

    @OneToOne(cascade = CascadeType.ALL)
    private FicheArboriculture ficheArboriculture;

    @OneToOne(cascade = CascadeType.ALL)
    private FicheDocuments ficheDocuments;

    @OneToOne(cascade = CascadeType.ALL)
    private FicheRessourcesEnEau ficheRessourcesEnEau;

    @OneToOne(cascade = CascadeType.ALL)
    private  IrrigationConfiguration irrigationConfiguration;

    @OneToOne(cascade = CascadeType.ALL)
    private  FicheDocumentFiles ficheDocumentFiles;

}
