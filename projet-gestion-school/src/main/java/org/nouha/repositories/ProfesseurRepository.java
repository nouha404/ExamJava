package org.nouha.repositories;

import java.util.List;

import org.nouha.entities.Professeur;

public interface ProfesseurRepository extends Repository<Professeur>{
    Professeur findById(List<Professeur> professeurs, int id);
}
