package org.nouha.repositories;

import java.util.List;

import org.nouha.entities.Salle;

public interface SalleRepository  extends Repository<Salle> {
    Salle findById(int id);
    List<Salle> findOneSalle(Salle salle);
    boolean modifierSalle(Salle salle);
    boolean archiverSalle(int id);
}
