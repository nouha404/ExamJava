package org.nouha.services;

import java.util.List;

import org.nouha.entities.Classe;
import org.nouha.entities.Cour;
import org.nouha.entities.Salle;

public interface ClasseService {
    boolean ajouterClasse(Classe classe);
    List<Classe> listerClasse();
    List<Salle> listerSalle();
    
}

