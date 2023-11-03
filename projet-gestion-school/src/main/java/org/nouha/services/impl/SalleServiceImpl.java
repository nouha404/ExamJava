package org.nouha.services.impl;

import java.util.List;

import org.nouha.entities.Cour;
import org.nouha.entities.Modules;
import org.nouha.entities.Salle;
import org.nouha.repositories.SalleRepository;
import org.nouha.services.SalleService;

public class SalleServiceImpl implements SalleService{

    private SalleRepository salleRepository;
    
    public SalleServiceImpl(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }

    @Override
    public boolean ajouterSalle(Salle salle) {
        return salleRepository.insert(salle)!=0;
    }

    @Override
    public Salle searchById(List<Salle> datasList, int id) {
        for (Salle salle : datasList) {
            if (salle.getId() == id) {
                return salle;
            }
        }
        return null;
    }

    @Override
    public List<Salle> listerSalles() {
        return salleRepository.findAll();
    }
    
    @Override
    public List<Salle> listerUneSalle(Salle salle) {
        //Salle saleFounded = salleRepository.findById(idSalle);
        return salleRepository.findOneSalle(salle);
    }

    @Override
    public boolean modifierSalle(Salle salle) {
        return salleRepository.modifierSalle(salle);
    }
    @Override
    public boolean archiverSalle(int id) {
        return salleRepository.archiverSalle(id);
    }


   
    
}
