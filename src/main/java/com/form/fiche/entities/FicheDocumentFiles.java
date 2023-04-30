package com.form.fiche.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FicheDocumentFiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] cinLegaliseeFile;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] devisFile;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] engagementFournisseurFile;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] portantFile;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] demandeExamenFile;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] contratBailFile;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] titreFoncierFile;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] certificatProprieteFile;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] acteLegaliseFile;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] planBornageFile;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] autorisationPuitsFile;
}
