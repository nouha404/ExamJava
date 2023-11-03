package org.nouha.services;

import java.util.List;

import org.nouha.entities.Modules;

public interface ModulesService {
    boolean ajouterModule(Modules module,int idClasse, int idCour);
    List<Modules> listerModules();
    Modules rechercherParId(int id);

    List<Modules> listerModulesDuneClasse(int idClasse);
}
