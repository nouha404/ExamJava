package org.nouha.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.nouha.entities.Classe;
import org.nouha.entities.Cour;
import org.nouha.entities.Professeur;
import org.nouha.entities.ENUM.TypeDisponibiliteCour;
import org.nouha.entities.ENUM.TypeEtatCour;
import org.nouha.repositories.CourRepository;
import org.nouha.repositories.Database;
import org.nouha.repositories.ProfesseurRepository;
import org.nouha.utils.ReadFileUtils;

public class CourRepositoryImpl implements CourRepository {
    //private final String SQL_SELECT_BY_PROFESSEUR= "SELECT id, dateCours, dateDebut, dateFin, disponibiliteCour, etatCour FROM `Cour` WHERE professeur_id = ?";
    //private final String SQL_SELECT_BY_CLASSE = "SELECT id, libelleClasse, salle_id FROM `Classe` WHERE cour_id = ?";
    private final  String SQL_SELECT_BY_ID = "SELECT * FROM Cour WHERE id = ?";
    //private final String SQL_SELECT_COUR_BY_CLASSE = "SELECT * FROM Cour WHERE id = ?";

    private Database database;
    private ProfesseurRepository professeurRepository;

  

    public CourRepositoryImpl(Database database, ProfesseurRepository professeurRepository) {
        this.database = database;
        this.professeurRepository = professeurRepository;
    }

    @Override
    public Cour findById(int id) {
        Cour cour = null;
        try {
            String driver = ReadFileUtils.getDriver();
            String url = ReadFileUtils.getUrl();
            String username = ReadFileUtils.getUsername();
            String password = ReadFileUtils.getPassword();
    
            database.openConnexion(driver, url, username, password);
            database.initPreparedStatement(SQL_SELECT_BY_ID);
            database.getPs().setInt(1, id);
            ResultSet resultSet = database.executeSelect();

            if (resultSet.next()) {
                cour = new Cour();
                Professeur prof = professeurRepository.findById(id);
                cour.setId(resultSet.getInt("id"));
                cour.setLibelleCour(resultSet.getString("libelleCour"));
                cour.setDateCour(resultSet.getDate("dateCour").toLocalDate());
                cour.setDateDebut(resultSet.getDate("dateDebut").toLocalDate());
                cour.setDateFin(resultSet.getDate("dateFin").toLocalDate());
                cour.setDisponibiliteCour(TypeDisponibiliteCour.valueOf(resultSet.getString("disponibiliteCour")));
                cour.setEtatCour(TypeEtatCour.valueOf(resultSet.getString("etatCour")));
                cour.setProfesseurs(prof);
            }
            resultSet.close();
            database.closeConnexion();
        } catch (SQLException e) {
            System.out.println("Erreur d'exécution de la requête : " + e.getMessage());
        }
        return cour;
    }

    @Override
    public void hidden(Cour data) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int insert(Cour data) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void update(Cour data) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public List<Cour> findAll() {
        // TODO Auto-generated method stub
        return null;
    }


    /*@Override
    public List<Cour> findCoursByProfesseur(Professeur professeur) {
        List<Cour> coursList = new ArrayList<>();
        try {
            String driver = ReadFileUtils.getDriver();
            String url = ReadFileUtils.getUrl();
            String username = ReadFileUtils.getUsername();
            String password = ReadFileUtils.getPassword();

            database.openConnexion(driver, url, username, password);
            database.initPreparedStatement(SQL_SELECT_BY_PROFESSEUR);
            database.getPs().setInt(1, professeur.getId());
            ResultSet resultSet = database.executeSelect();
            while (resultSet.next()) {
                Cour cours = new Cour();

                cours.setId(resultSet.getInt("id"));
                cours.setDateCour(resultSet.getDate("dateCours").toLocalDate());
                cours.setDateDebut(resultSet.getDate("dateDebut").toLocalDate());
                cours.setDateFin(resultSet.getDate("dateFin").toLocalDate());
                cours.setDisponibiliteCour(TypeDisponibiliteCour.valueOf(resultSet.getString("disponibiliteCour")));
                cours.setEtatCour(TypeEtatCour.valueOf(resultSet.getString("etatCour")));
                coursList.add(cours);
            }
            resultSet.close();
            database.closeConnexion();
        } catch (SQLException e) {
            System.out.println("Erreur d'exécution de la requête");
        }
        return coursList;
    }*/

    @Override
    public List<Cour> findListCourById(int id) {
        List<Cour> coursList = new ArrayList<>();
        try {
            String driver = ReadFileUtils.getDriver();
            String url = ReadFileUtils.getUrl();
            String username = ReadFileUtils.getUsername();
            String password = ReadFileUtils.getPassword();

            database.openConnexion(driver, url, username, password);
            database.initPreparedStatement(SQL_SELECT_BY_ID);
            database.getPs().setInt(1, id);
            ResultSet resultSet = database.executeSelect();

            if (resultSet.next()) {
                Cour cour = new Cour();
                Professeur prof = professeurRepository.findById(id);

                cour.setId(resultSet.getInt("id"));
                cour.setLibelleCour(resultSet.getString("libelleCour"));
                cour.setDateCour(resultSet.getDate("dateCours").toLocalDate());
                cour.setDateDebut(resultSet.getDate("dateDebut").toLocalDate());
                cour.setDateFin(resultSet.getDate("dateFin").toLocalDate());
                cour.setDisponibiliteCour(TypeDisponibiliteCour.valueOf(resultSet.getString("disponibiliteCour")));
                cour.setEtatCour(TypeEtatCour.valueOf(resultSet.getString("etatCour")));
                cour.setProfesseurs(prof);
                coursList.add(cour);
            }
            resultSet.close();
            database.closeConnexion();
        } catch (SQLException e) {
            System.out.println("Erreur d'exécution de la requête");
        }
        return coursList;
        }


    /*@Override
    public List<Cour> findCoursList(int idCour) {
        List<Cour> coursList = new ArrayList<>();
        try {
            String driver = ReadFileUtils.getDriver();
            String url = ReadFileUtils.getUrl();
            String username = ReadFileUtils.getUsername();
            String password = ReadFileUtils.getPassword();
    
            database.openConnexion(driver, url, username, password);
            database.initPreparedStatement(SQL_SELECT_COUR_BY_CLASSE);
            database.getPs().setInt(1, idCour);
            ResultSet resultSet = database.executeSelect();
            while (resultSet.next()) {
                Cour cour = new Cour();
                Professeur prof = professeurRepository.findById(idCour);

                cour.setId(resultSet.getInt("id"));
                cour.setLibelleCour(resultSet.getString("libelleCour"));
                cour.setDateCour(resultSet.getDate("dateCours").toLocalDate());
                cour.setDateDebut(resultSet.getDate("dateDebut").toLocalDate());
                cour.setDateFin(resultSet.getDate("dateFin").toLocalDate());
                cour.setDisponibiliteCour(TypeDisponibiliteCour.valueOf(resultSet.getString("disponibiliteCour")));
                cour.setEtatCour(TypeEtatCour.valueOf(resultSet.getString("etatCour")));
                cour.setProfesseurs(prof);
                coursList.add(cour);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Erreur d'exécution de la requête : " + e.getMessage());
        } finally {
            database.closeConnexion();
        }
        return coursList;
        }*/
    

    }

