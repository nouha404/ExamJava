package org.nouha.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Module {
    private int id;
    private String libelleModule;

    Classe classes;
    Cour cours;
    List<Professeur> professeurs;
}
