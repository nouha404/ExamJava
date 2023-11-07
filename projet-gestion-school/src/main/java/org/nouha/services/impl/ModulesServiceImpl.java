package org.nouha.services.impl;


import java.util.List;

import org.nouha.entities.Classe;
import org.nouha.entities.Modules;
import org.nouha.entities.Professeur;
import org.nouha.repositories.ModulesRepository;
import org.nouha.services.ModulesService;

public class ModulesServiceImpl implements ModulesService {
    private ModulesRepository moduleRepository;
    
   
    public ModulesServiceImpl(ModulesRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    @Override
    public boolean ajouterModule(Modules modules) {
        return moduleRepository.insert(modules)!=0;
    }

    @Override
    public List<Modules> listerModules() {
        return moduleRepository.findAll();
    }

    @Override
    public List<Modules> listerMParClasse(Classe cls) {
        return moduleRepository.listerModulesParClasse(cls);
        
    }

   
    /*

    @Override
    public boolean ajouterModule(Modules module,int idClasse, int idCour) {
        //return moduleRepository.insert(module)!=0;
        if (module == null) {
            System.out.println("Données de module manquantes ou nulles.");
            return false;
        }
        if (module.getClasses() == null) {
            module.setClasses(new Classe());
        }
        if (module.getCours() == null) {
            module.setCours(new Cour());
        }
        if (module.getClasses().getId() != idClasse && module.getCours().getId() != idCour) {
            Classe existingClasse = classeRepository.findById(idClasse); 
            if (existingClasse == null) {
                existingClasse = new Classe();
                int insertedClasseId = classeRepository.insert(existingClasse);
                existingClasse.setId(insertedClasseId);
            }
            Cour existingCour = courRepository.findById(idCour);
    
            if (existingCour == null) {
                existingCour = new Cour(); 
                int insertedCourId = courRepository.insert(existingCour);
                existingCour.setId(insertedCourId);
            }
            module.setClasses(existingClasse);
            module.setCours(existingCour);

            return moduleRepository.insert(module) != 0;
        } else {
            System.out.println("Les valeurs d'ID sont nulles.");
            return false;
        }
    }

    

    @Override
    public Modules rechercherParId(int id) {
       return moduleRepository.findById(id);
}

    @Override
    public List<Modules> listerModulesDuneClasse(int idClasse) {
        Classe classe = classeRepository.findById(idClasse);
        return moduleRepository.listerModulesParClasse(classe);
    }
    
    */

    
}
