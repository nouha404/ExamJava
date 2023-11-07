package org.nouha.services;

import java.util.List;

import org.nouha.entities.Classe;

public interface ClasseService {
    List<Classe> listerClasse();
    List<Classe> listerUneClasse(Classe classe);
    boolean ajouterClasse(Classe classe);
    boolean modifierClasse(Classe classe);
    boolean archiverClasse(int id);
    /*
    boolean ajouterClasse(Classe classe);
    
    List<Salle> listerSalle();
    List<Classe> listerUneClasse(Classe classe);
    boolean modifierClasse(Classe classe);
    boolean archiverClasse(int id);
    List<Modules>  listerClasseDunModule(int idClass);
    */
}

