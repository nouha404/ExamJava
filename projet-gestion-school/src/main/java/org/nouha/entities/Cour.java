package org.nouha.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nouha.entities.ENUM.TypeDisponibiliteCour;
import org.nouha.entities.ENUM.TypeEtatCour;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Cour {
    private int id;
    private String libelleCour;
    private LocalDate dateCour;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    private TypeDisponibiliteCour disponibiliteCour;
    private TypeEtatCour etatCour;

    //ManyToOne
    Classe classes;
    Professeur professeurs;

    public Cour(int id) {
        this.id = id;
    }

}
