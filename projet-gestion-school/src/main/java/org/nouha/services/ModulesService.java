package org.nouha.services;

import java.util.List;

import org.nouha.entities.Classe;
import org.nouha.entities.Modules;
import org.nouha.entities.Professeur;
import org.nouha.entities.Salle;

public interface ModulesService {
   
    boolean ajouterModule(Modules modules);
    List<Modules> listerModules();
    List<Modules> listerMParClasse(Classe cls);
   /* boolean ajouterModule(Modules module,int idClasse, int idCour);
  
    Modules rechercherParId(int id);
    
    List<Modules> listerModulesDuneClasse(int idClasse);*/
}
