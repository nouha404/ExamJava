package org.nouha.repositories.impl;

import java.util.List;

import org.nouha.entities.Professeur;
import org.nouha.repositories.ProfesseurRepository;

public class ProfesseurRepositoryImpl implements ProfesseurRepository {

    ProfesseurRepository professeurRepository;
    public ProfesseurRepositoryImpl(ProfesseurRepository professeurRepository) {
        this.professeurRepository = professeurRepository;
    }

    @Override
    public List<Professeur> findAll() {
        // TODO Auto-generated method stub  
        return null;
    }

    @Override
    public void hidden(Professeur data) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int insert(Professeur data) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void update(Professeur data) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Professeur findById(List<Professeur> professeurs, int id) {
        for (Professeur professeur : professeurs) {
            if (professeur.getId() == id) {
                return professeur;
            }
        }
        return null;
    }
}
