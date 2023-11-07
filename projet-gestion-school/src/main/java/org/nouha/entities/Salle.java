package org.nouha.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Salle {
    private int id;
    private String libelleSalle;
    private double capacite;
    private int numeroSalle;
    private boolean isArchived;

    //OneToOne
    Classe classes;

    public Salle(String libelleSalle, double capacite, int numeroSalle, boolean isArchived, Classe classes) {
        this.libelleSalle = libelleSalle;
        this.capacite = capacite;
        this.numeroSalle = numeroSalle;
        this.isArchived = isArchived;
        this.classes = classes;
    }

    public Salle(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-30s | %-10s | %-15s | %-10s | %-20s |",
                id, libelleSalle, capacite, numeroSalle, isArchived, classes);
    }



    

  
}
