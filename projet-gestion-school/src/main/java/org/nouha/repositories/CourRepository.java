package org.nouha.repositories;

import java.util.List;

import org.nouha.entities.Classe;
import org.nouha.entities.Cour;
import org.nouha.entities.Professeur;

public interface CourRepository extends Repository<Cour> {
    Cour findById(int id);
    List<Cour> findCoursByProfesseur(Professeur professeur);
    public List<Cour> findCourById(int id);

    List<Cour> findCoursByClasse(int idClasse);

    /*    
    //classes.setSalle(Salles);
    /* cours.se(resultSet.getString("libelleClasse"));
    cours.setDateDebut(resultSet.getDate("dateDebut").toLocalDate());
    cours.setDateFin(resultSet.getDate("dateFin").toLocalDate());
    cours.setDisponibiliteCour(TypeDisponibiliteCour.valueOf(resultSet.getString("disponibiliteCour")));
    cours.setEtatCour(TypeEtatCour.valueOf(resultSet.getString("etatCour")));  */
}
