package org.nouha.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nouha.entities.ENUM.TypeMatiereEnseigner;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Professeur {
    private int id;
    private String nomComplet;
    private TypeMatiereEnseigner matiereEnseigner;
    private boolean archive;
    List<Cour> cours;

    public Professeur(int id, String nomComplet, TypeMatiereEnseigner matiereEnseigner, boolean archive) {
        this.id = id;
        this.nomComplet = nomComplet;
        this.matiereEnseigner = matiereEnseigner;
        this.archive = archive;
    }

    public Professeur(int id, String nomComplet) {
        this.id = id;
        this.nomComplet = nomComplet;
    }
}
