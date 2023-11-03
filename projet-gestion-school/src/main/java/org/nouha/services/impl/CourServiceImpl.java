package org.nouha.services.impl;

import java.util.List;

import org.nouha.entities.Cour;
import org.nouha.entities.Professeur;
import org.nouha.repositories.CourRepository;
import org.nouha.services.CourService;

public class CourServiceImpl implements CourService {

    CourRepository courRepository;
    public CourServiceImpl(CourRepository courRepository) {
        this.courRepository = courRepository;
    }
    @Override
    public Cour searchById(List<Cour> datasList, int id) {
        for (Cour cour : datasList) {
            if (cour.getId() == id) {
                return cour;
            }
        }
        return null;
    }
    @Override
    public List<Cour> ListerCourParProfesseur(Professeur prof) {
        return courRepository.findCoursByProfesseur(prof);

    }
  
    
}
