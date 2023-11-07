package org.nouha.repositories;

import java.util.List;

import org.nouha.entities.Cour;

public interface CourRepository extends Repository<Cour> {
    Cour findById(int id);
    //List<Cour> findCoursByProfesseur(Professeur professeur);
    public List<Cour> findListCourById(int id);

    
}
