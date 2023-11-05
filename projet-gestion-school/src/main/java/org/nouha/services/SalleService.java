package org.nouha.services;

import java.util.List;

import org.nouha.entities.Salle;

public interface SalleService extends Service<Salle>{
    List<Salle> listerSalles();
    List<Salle> listerUneSalle(Salle salle);
    boolean ajouterSalle(Salle salle);
    boolean modifierSalle(Salle salle);
    boolean archiverSalle(int id);
}
