package org.nouha.services.impl;

import java.util.List;


import org.nouha.entities.Classe;
import org.nouha.entities.Cour;
import org.nouha.entities.Modules;
import org.nouha.entities.Professeur;
import org.nouha.entities.Salle;
import org.nouha.repositories.ClasseRepository;
import org.nouha.repositories.SalleRepository;
import org.nouha.services.ClasseService;
import org.nouha.services.CourService;
import org.nouha.services.SalleService;

public class ClasseServiceImpl implements ClasseService {
    SalleService salleService;
    CourService courService;
    ClasseRepository classeRepository;
    SalleRepository salleRepository;
    //injection
    public ClasseServiceImpl(SalleService salleService, CourService courService, ClasseRepository classeRepository,SalleRepository salleRepository) {
        this.salleService = salleService;
        this.courService = courService;
        this.classeRepository = classeRepository;
        this.salleRepository = salleRepository;
    }

    @Override
    public List<Classe> listerClasse() {
        return classeRepository.findAll();
    }

    @Override
    public List<Classe> listerUneClasse(Classe classe) {
        System.out.println("");
        System.out.println(String.format("| %-3s | %-30s | %-10s |",
                                "Id", "Libelle Classe", "isArchived"));
        return classeRepository.findOneClasse(classe);
    } 

    @Override
    public boolean ajouterClasse(Classe classe) {
        return classeRepository.insert(classe)!=0;
    }

    @Override
    public boolean modifierClasse(Classe classe) {
        return classeRepository.modifierClasse(classe);
    }

    @Override
    public boolean archiverClasse(int id) {
        return classeRepository.archiverClasse(id);
    }
    
   /*
    
    @Override
    public boolean ajouterClasse(Classe Classe) {
        return classeRepository.insert(classe)!=0;
    }



    @Override
    public boolean ajouterClasse(Classe classe) {
        return classeRepository.insert(classe)!=0;
    }



    @Override
    public List<Salle> listerSalle() {
        return salleRepository.findAll();
    }

    @Override
    public List<Classe> listerUneClasse(Classe classe) {
        return classeRepository.findOneClasse(classe);
    }
    
    @Override
    public boolean modifierClasse(Classe classe) {
        return classeRepository.modifierClasse(classe);
    } 
    
    @Override
    public boolean archiverClasse(int id) {
        return classeRepository.archiverClasse(id);
    }



    @Override
    public List<Modules> listerClasseDunModule(int idClass) {
        return classeRepository.findModuleByClasseID(idClass);
    }


*/
   
}
