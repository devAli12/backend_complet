package com.form.fiche.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FicheFormDTO {

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
    private String maraichage;
    private String culture;
    private Double distanceEntreLignes;
    private Double distanceEntrePlante;
    private Integer nbreDeRempesParLigne;
    private String sensDePlantation;

    private String arboricultureCulture;
    private Double distanceEntreLignesDePlantation;
    private Double distanceEntreLesArabres;
    private Integer arboricultureNbreDeRempesParLigne;
    private String sensDePlanatationSupl;
    private String autres;
    private String nbreDeGoutteursParArabre;
    private String choixClient;
    private String choixaProposer;
    private String typeGoutteur;
    private String marque;
    private Double debit;
    private Double espacement;
    private String autorisationPompage;
    private Integer nombrePuits;
    private List<RessourceDTO> puitData;
    private Integer nombreForages;
    private List<RessourceDTO> forageData;
    private Integer nombreBornes;
    private List<RessourceDTO> borneIrrigationData;
    private Boolean existeBassin;
    private Double longueur;
    private Double largeur;
    private Double hauteur;
    private Boolean aproposer;
    private Double capacite;
    private Double dimension;
    private String filtration;
    private String commandeDesVannes;
    private String fertigation;
    private String agitationDesEngrais;
    private String pompeDeReprise;
    private String demarragePompeDeReprise;
    private String pompeDeRemplissage;
    private String demarragePompeDeRemplissage;
    private String nombreEtVolumeDesCiternes;
    private String observation;

    private boolean saveInProgress;

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


