package org.nouha.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Modules {
    private int id;
    private String libelleModule;

    Classe classes;
    Cour cours;

    public Modules(int id, String libelleModule, int classe_id, int cour_id) {
        this.id = id;
        this.libelleModule = libelleModule;
        this.classes = new Classe();
        this.classes.setId(classe_id);
        this.cours = new Cour();
        this.cours.setId(cour_id);
    }

}
