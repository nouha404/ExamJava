package org.nouha.repositories;

import java.util.List;

import org.nouha.entities.Classe;
import org.nouha.entities.Modules;
import org.nouha.entities.Professeur;

public interface ModulesRepository extends Repository<Modules> {
    //List<Modules> findModuleByClasse(Classe classe);
    Modules findById(int id);
    //List<Modules> modulesListById(int idModule);
    //List<Modules> listerModules(Modules modules);
    List<Modules> listerModulesParClasse(Classe classe);
    //List<Modules> listerModulesParProfesseur(Professeur professeur);
    List<Modules> getModuleList(Modules module);
    List<Modules> listerModulesParProfesseur(Professeur professeur);

    List<Modules> findModuleByProfesseur(Professeur professeur);

    
}
