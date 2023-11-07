package org.nouha.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.nouha.entities.Classe;
import org.nouha.entities.Salle;
import org.nouha.repositories.ClasseRepository;
import org.nouha.repositories.Database;
import org.nouha.repositories.SalleRepository;
import org.nouha.utils.ReadFileUtils;

public class SalleRepositoryImpl implements SalleRepository {
    private final String SQL_INSERT = "INSERT INTO Salle(libelleSalle, capacite, numeroSalle, isArchived) VALUES (?, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE Salle SET libelleSalle = ?, capacite = ?, numeroSalle = ?, isArchived = ?  WHERE id = ?";
    private final String  SQL_SELECT_ALL="SELECT * FROM Salle";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM Salle WHERE id = ?";

    private final String SQL_ARCHIVE = "UPDATE Salle SET isArchived = ? WHERE id = ?";


    private Database database;
    ClasseRepository classeRepository;
    
    //injection de dependance
    public SalleRepositoryImpl(Database database, ClasseRepository classeRepository) {
        this.database = database;
        this.classeRepository = classeRepository;
    }


    public SalleRepositoryImpl(Database database) {
        this.database = database;
    }
    

    @Override
    public List<Salle> findAll() {
        List<Salle> SalleList = new ArrayList<>();
        try {
            String driver = ReadFileUtils.getDriver();
            String url = ReadFileUtils.getUrl();
            String username = ReadFileUtils.getUsername();
            String password = ReadFileUtils.getPassword();
            database.openConnexion(driver, url, username, password);
            database.initPreparedStatement(SQL_SELECT_ALL);
            ResultSet resultSet = database.executeSelect();

            while (resultSet.next()) {
                boolean isArchived = resultSet.getBoolean("isArchived");
                if (!isArchived) {
                    Salle salle = new Salle(
                        resultSet.getInt("id"),
                        resultSet.getString("libelleSalle"),
                        resultSet.getDouble("capacite"),
                        resultSet.getInt("numeroSalle"),
                        isArchived,
                        null
                    );
                    SalleList.add(salle);
                }
            }
            resultSet.close();
            database.closeConnexion();
        } catch (SQLException e) {
            System.out.println("Erreur d'exécution de la requête" + e.getMessage());
        }
        return SalleList;
    }


    @Override
    public void hidden(Salle data) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int insert(Salle data) {
    int lastInsertId = 0;
        try {
            String driver = ReadFileUtils.getDriver();
            String url = ReadFileUtils.getUrl();
            String username = ReadFileUtils.getUsername();
            String password = ReadFileUtils.getPassword();
            
            database.openConnexion(driver, url, username, password);
            database.initPreparedStatement(SQL_INSERT);
            database.getPs().setString(1, data.getLibelleSalle());
            database.getPs().setDouble(2, data.getCapacite());
            database.getPs().setInt(3, data.getNumeroSalle());
            database.getPs().setBoolean(4, data.isArchived());
            
            lastInsertId = database.executeUpdate();
            database.closeConnexion();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'exécution de la requête d'insertion : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Une erreur inattendue s'est produite : " + e.getMessage());
        }
    return lastInsertId;
    }



    @Override
    public void update(Salle data) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public Salle findById(int id) {
        Salle salle = null;
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
                int classeId = resultSet.getInt("classe_id");
                Classe classe = classeRepository.findById(classeId);

                salle = new Salle(
                    resultSet.getInt("id"),
                    resultSet.getString("libelleSalle"),
                    resultSet.getDouble("capacite"),
                    resultSet.getInt("numeroSalle"),
                    resultSet.getBoolean("isArchived"),
                    classe
                );
                
            }
            resultSet.close();
            database.closeConnexion();
        } catch (SQLException e) {
            System.out.println("Erreur d'exécution de la requête" + e.getMessage());
        }
        return salle;
    }
    
     @Override
    public List<Salle> findOneSalle(Salle salle) {
        List<Salle> salleList = new ArrayList<>();
        try {
            
            String driver = ReadFileUtils.getDriver();
            String url = ReadFileUtils.getUrl();
            String username = ReadFileUtils.getUsername();
            String password = ReadFileUtils.getPassword();

            database.openConnexion(driver, url, username, password);
            database.initPreparedStatement(SQL_SELECT_BY_ID);
            database.getPs().setInt(1, salle.getId());
            ResultSet resultSet = database.executeSelect();
            while (resultSet.next()) {
                Salle salles = new Salle();

                
                salles.setId(resultSet.getInt("id"));
                salles.setLibelleSalle(resultSet.getString("libelleSalle"));
                salles.setCapacite(resultSet.getDouble("capacite"));
                salles.setNumeroSalle(resultSet.getInt("numeroSalle"));
                salles.setArchived(resultSet.getBoolean("isArchived"));
                
                //recupere la classe correpondant
                int classeId = resultSet.getInt("classe_id");
                Classe classe = classeRepository.findById(classeId);
                salles.setClasses(classe);
                salleList.add(salles);
            }
            resultSet.close();
            database.closeConnexion();
        } catch (SQLException e) {
            System.out.println("Erreur d'exécution de la requête "+ e.getMessage());
        }
        return salleList;
    }


    @Override
    public boolean modifierSalle(Salle salle) {
        try {
            String driver = ReadFileUtils.getDriver();
            String url = ReadFileUtils.getUrl();
            String username = ReadFileUtils.getUsername();
            String password = ReadFileUtils.getPassword();

            database.openConnexion(driver, url, username, password);
            database.initPreparedStatement(SQL_UPDATE);

            database.getPs().setString(1, salle.getLibelleSalle());
            database.getPs().setDouble(2, salle.getCapacite());
            database.getPs().setInt(3, salle.getNumeroSalle());
            database.getPs().setBoolean(4, salle.isArchived());
            database.getPs().setInt(5, salle.getId());

            int rowsAffected = database.executeUpdate();
            database.closeConnexion();

            if (rowsAffected > 0) {
                System.out.println("La salle a été modifiée avec succès.");
                return true;
            } else {
                System.out.println("Aucune salle n'a été modifiée. Vérifiez les paramètres de mise à jour.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL lors de la mise à jour de la salle : " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Une erreur inattendue s'est produite lors de la mise à jour de la salle : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean archiverSalle(int id) {
        try {
            String driver = ReadFileUtils.getDriver();
            String url = ReadFileUtils.getUrl();
            String username = ReadFileUtils.getUsername();
            String password = ReadFileUtils.getPassword();

            database.openConnexion(driver, url, username, password);
            
            database.initPreparedStatement(SQL_ARCHIVE);
            database.getPs().setBoolean(1, true); // true indique que la salle est archivée
            database.getPs().setInt(2, id);

            int rowsAffected = database.executeUpdate();
            database.closeConnexion();
            
            if (rowsAffected > 0) {
                System.out.println("La salle a été archivée avec succès.");
                return true;
            } else {
                System.out.println("Aucune salle n'a été archivée. Vérifiez l'ID de la salle.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erreur execution de la requeteSQL : " + e.getMessage());
            return false;
        }
    }


    

}
