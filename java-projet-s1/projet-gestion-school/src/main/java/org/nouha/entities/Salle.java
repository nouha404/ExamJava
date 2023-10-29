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

    //OneToMany
    List<Classe> classes;
}
