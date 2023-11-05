package org.nouha.services;

import java.util.List;

import org.nouha.entities.Classe;
import org.nouha.entities.Modules;
import org.nouha.entities.Salle;

public interface ClasseService {
    boolean ajouterClasse(Classe classe);
    List<Classe> listerClasse();
    List<Salle> listerSalle();
    List<Classe> listerUneClasse(Classe classe);
    boolean modifierClasse(Classe classe);
    boolean archiverClasse(int id);
    List<Modules>  listerClasseDunModule(int idClass);
    
}

