package org.nouha.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
//@Data
@Getter
@Setter
@NoArgsConstructor
public class Classe {
    private int id;
    private String libelleClasse;
    private boolean archive;

    //ManyToOne
    Salle salle;
    Cour cour;
    public Classe(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "id\t\n"+id + "\n"+ " Classe\t"+ libelleClasse+"\n  archive\t" +archive+"\n Salle\t "+salle.getLibelleSalle()+"\n Cour\t"+cour.getEtatCour()+"\n";
    }
}
