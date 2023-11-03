package org.nouha.repositories;

import java.util.List;

import org.nouha.entities.Classe;
import org.nouha.entities.Modules;

public interface ModulesRepository extends Repository<Modules> {
    List<Modules> findModuleByClasse(Classe classe);
    Modules findById(int id);
    List<Modules> listerModules(Modules modules);
    List<Modules> listerModulesParClasse(Classe classe);
}
