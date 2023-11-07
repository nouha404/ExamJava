package org.nouha.repositories;

import java.util.List;

import org.nouha.entities.Classe;
import org.nouha.entities.Modules;

public interface ClasseRepository extends Repository<Classe> {
    int insert(Classe data);
    List<Classe> findAll();
    Classe findById(int id);
    List<Classe> getclassList(Classe classe);
    boolean modifierClasse(Classe classe);
    
    boolean archiverClasse(int id);

    List<Modules> findModuleByID(int moduleID);
    List<Classe> findOneClasse(Classe classe);

    


    
}
