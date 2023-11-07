package org.nouha.repositories;

import java.util.List;
import java.util.Scanner;

import org.nouha.entities.Professeur;

public interface ProfesseurRepository extends Repository<Professeur>{
    Professeur findById(int id);
    //Professeur saisirNouveauProfesseur(Scanner sc);
}
