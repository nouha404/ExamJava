package org.nouha.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
//@Data
@Getter
@Setter
@NoArgsConstructor
public class Classe {
    private int id;
    private String libelleClasse;
    private boolean archive;
    
    //OneToMany
    Salle salles;
    List<Cour> cours;
    
    //ManyToMany
    List<Modules> modules;
  

    public Classe(int id) {
        this.id = id;
    }

    public Classe(int id, String libelleClasse, boolean archive, Salle salles) {
        this.id = id;
        this.libelleClasse = libelleClasse;
        this.archive = archive;
        this.salles = salles;
    }

    public Classe(int id, String libelleClasse, boolean archive) {
        this.id = id;
        this.libelleClasse = libelleClasse;
        this.archive = archive;
    }

    public Classe(int id, String libelleClasse) {
        this.id = id;
        this.libelleClasse = libelleClasse;
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-30s | %-10s |",
                id, libelleClasse, archive);
    }
}
