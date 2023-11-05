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
    private boolean archive;
    Classe classes;
    Cour cours;


    public Modules(int id, String libelleModule) {
        this.id = id;
        this.libelleModule = libelleModule;
    }


    public Modules(int id, String libelleModule, boolean archive) {
        this.id = id;
        this.libelleModule = libelleModule;
        this.archive = archive;
    }


    public Modules(int id, String libelleModule,boolean archive, int classe_id, int cour_id) {
        this.id = id;
        this.libelleModule = libelleModule;
        this.archive = archive;
        this.classes = new Classe();
        this.classes.setId(classe_id);
        this.cours = new Cour();
        this.cours.setId(cour_id);
    }

}
