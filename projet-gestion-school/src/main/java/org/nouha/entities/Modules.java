package org.nouha.entities;

import java.util.List;

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

    //ManyToMany
    List<Classe> classes;
    //ManyToOne
    Professeur professeurs;


    public Modules(int id, String libelleModule, boolean archive) {
        this.id = id;
        this.libelleModule = libelleModule;
        this.archive = archive;
    }

    public Modules(int id, String libelleModule) {
        this.id = id;
        this.libelleModule = libelleModule;
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-30s | %-10s |",
                id, libelleModule, archive);
    }
  

}
