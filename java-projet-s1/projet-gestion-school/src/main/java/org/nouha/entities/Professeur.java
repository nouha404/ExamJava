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

    List<Module> modules;
    List<Cour> cours;
}
