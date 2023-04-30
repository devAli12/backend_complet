package com.form.fiche.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FicheRessourcesEnEau {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    Long id;

    private String autorisationPompage;
    private Integer nombreBornes;

    private Integer nombreForages;

    private Integer nombrePuits;
    @OneToMany(mappedBy = "ficheRessourcesEnEau",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<BorneIrrigation> bornesIrrigation;

    @OneToMany(mappedBy = "ficheRessourcesEnEau",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Puit> puits;

    @OneToMany(mappedBy = "ficheRessourcesEnEau",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Forage> forages;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FicheRessourcesEnEau fiche)) return false;
        return Objects.equals(id, fiche.id);
    }

    @OneToOne(cascade = CascadeType.ALL)
    private Bassin bassin;

}
