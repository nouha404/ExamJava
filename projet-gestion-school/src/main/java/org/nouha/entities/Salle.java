package org.nouha.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Salle {
    private int id;
    private String libelleSalle;
    private double capacite;
    private int numeroSalle;
    private boolean archive;

    //OneToMany
    List<Classe> classes;

    public Salle(String libelleSalle, double capacite, int numeroSalle, boolean archive, List<Classe> classes) {
        this.libelleSalle = libelleSalle;
        this.capacite = capacite;
        this.numeroSalle = numeroSalle;
        this.archive = archive;
        this.classes = classes;
    }

    public Salle(int id) {
        this.id = id;
    }

  
}
