package org.nouha.repositories;

import java.util.List;

import org.nouha.entities.Classe;
import org.nouha.entities.Modules;
import org.nouha.entities.Professeur;

public interface ClasseRepository extends Repository<Classe> {
    List<Classe> findClasseById(int classeId);
    List<Classe> findAll();
    List<Classe> listerClasses();
    Classe findById(int id);
    List<Classe> findOneClasse(Classe classe);
    boolean modifierClasse(Classe classe);
    boolean archiverClasse(int id);

    List<Modules> findModuleByClasseID(int classeId);
    Professeur findProfByCourID(int courID);
    

    /* private int id;
    private String nomComplet;
    private TypeMatiereEnseigner matiereEnseigner;
    private boolean archive;
    List<Cour> cours; */
    //Afficher les classes d'un professeur ainsi que ses modules enseignés 
    //je dois passer par cours , il ya professeur_id laba 
    //ainsi je pourrai acceder a la classe Prof et y recuperer son nom et la matiere enseigner
    //Dans Classe il ya cour_id



    
}
