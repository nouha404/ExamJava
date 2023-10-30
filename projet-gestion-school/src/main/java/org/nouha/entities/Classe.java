package org.nouha.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Classe {
    private int id;
    private String libelleClasse;

    //ManyToOne
    Salle salle;
    Cour cour;
}
