package org.nouha.services.impl;

import java.util.List;


import org.nouha.entities.ENUM.TypeMatiereEnseigner;
import org.nouha.repositories.ClasseRepository;
import org.nouha.repositories.ModulesRepository;
import org.nouha.services.ProfesseurService;

public class ProfesseurServiceImpl implements ProfesseurService {

    private ClasseRepository classeRepository;
    private ModulesRepository modulesRepository;

    public ProfesseurServiceImpl(ClasseRepository classeRepository, ModulesRepository modulesRepository) {
        this.classeRepository = classeRepository;
        this.modulesRepository = modulesRepository;
    }

    @Override
    public void afficherOptionsMatiereEnseigner() {
        System.out.println("Veuillez choisir la matière à enseigner parmi les options suivantes :");
        for (TypeMatiereEnseigner matiere : TypeMatiereEnseigner.values()) {
            System.out.println(matiere);
        }
        
    } 

    /*@Override
    public List<Classe> listerClassesAndModulesEnseignes(int idProfesseur) {
        // Récupérer les classes du professeur à partir du référentiel
        List<Classe> classesEnseignees = classeRepository.findClassesByProfesseurId(idProfesseur);

        // Pour chaque classe, récupérer les modules enseignés par le professeur
        for (Classe classe : classesEnseignees) {
            List<Modules> modulesEnseignes = modulesRepository.findModulesByProfesseurAndClasse(idProfesseur, classe.getId());
            classe.setModulesEnseignes(modulesEnseignes);
        }

        return classesEnseignees;
    }*/

}
