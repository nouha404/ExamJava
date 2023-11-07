package org.nouha.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nouha.entities.ENUM.TypeMatiereEnseigner;

import java.util.List;

@Data
@NoArgsConstructor
public class Professeur {
    private int id;
    private String nomComplet;
    private TypeMatiereEnseigner matiereEnseigner;
    private boolean archive;

    //OneToMany
    List<Cour> cours;
    List<Modules> modules;
    
    public Professeur(int id, String nomComplet, TypeMatiereEnseigner matiereEnseigner, boolean archive) {
        this.id = id;
        this.nomComplet = nomComplet;
        this.matiereEnseigner = matiereEnseigner;
        this.archive = archive;
    }

    public Professeur(int id, String nomComplet, TypeMatiereEnseigner matiereEnseigner, boolean archive,
            List<Cour> cours, List<Modules> modules) {
        this.id = id;
        this.nomComplet = nomComplet;
        this.matiereEnseigner = matiereEnseigner != null ? matiereEnseigner : TypeMatiereEnseigner.MATHEMATIQUE;
        this.archive = archive;
        this.cours = cours;
        this.modules = modules;
    }

    public Professeur(int id) {
        this.id = id;
    }

   
}
