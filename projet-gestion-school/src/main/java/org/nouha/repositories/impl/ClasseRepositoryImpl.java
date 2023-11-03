package org.nouha.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.nouha.entities.Classe;
import org.nouha.entities.Cour;
import org.nouha.entities.Salle;
import org.nouha.repositories.ClasseRepository;
import org.nouha.repositories.SalleRepository;
import org.nouha.repositories.CourRepository;
import org.nouha.repositories.Database;
import org.nouha.utils.ReadFileUtils;

public class ClasseRepositoryImpl implements ClasseRepository {
    private final String  SQL_SELECT_BY="SELECT id,libelleClasse, salle_id, cour_id FROM `Classe` where id=?";
    private final String  SQL_SELECT_ALL="SELECT * FROM Classe";
    private final String SQL_INSERT = "INSERT INTO Classe(libelleClasse, salle_id, cour_id) VALUES (?, ?, ?)";
    private Database database;
    private SalleRepository salleRepository;
    private CourRepository courRepository;

    //injection dependance
    public ClasseRepositoryImpl(Database database, SalleRepository salleRepository, CourRepository courRepository) {
        this.database = database;
        this.salleRepository = salleRepository;
        this.courRepository = courRepository;
    }

    @Override
    public List<Classe> findAll() {
        List<Classe> classesList = new ArrayList<>();
        try {
            String driver = ReadFileUtils.getDriver();
            String url = ReadFileUtils.getUrl();
            String username = ReadFileUtils.getUsername();
            String password = ReadFileUtils.getPassword();
            database.openConnexion(driver, url, username, password);
            database.initPreparedStatement(SQL_SELECT_ALL);
            ResultSet resultSet = database.executeSelect();

            while (resultSet.next()) {
                int salleId = resultSet.getInt("salle_id");
                int courId = resultSet.getInt("cour_id");
                
    
                // Assuming you have the necessary repository methods to find Salle and Cour objects
                Salle salle = salleRepository.findById(salleId);
                //System.out.println(salle);
                Cour cour = courRepository.findById(courId);
    
                Classe classe = new Classe(
                    resultSet.getInt("id"), 
                    resultSet.getString("libelleClasse"), 
                    salle, 
                    cour
                    );
                classesList.add(classe);
            }
            resultSet.close();
            database.closeConnexion();
        } catch (SQLException e) {
            System.out.println("Erreur d'exécution de la requête" + e.getMessage());
        }
        return classesList;
        
    }

    @Override
    public void hidden(Classe data) {
        // TODO Auto-generated method stub
        
    }

    @Override
public int insert(Classe data) {
    if (data.getId() == 0) {
        try {
            String driver = ReadFileUtils.getDriver();
            String url = ReadFileUtils.getUrl();
            String username = ReadFileUtils.getUsername();
            String password = ReadFileUtils.getPassword();

            database.openConnexion(driver, url, username, password);
            
            Salle salle = salleRepository.findById(data.getSalle().getId());
            Cour cour = courRepository.findById(data.getCour().getId());

            data.setSalle(salle);
            data.setCour(cour);
            
            database.initPreparedStatement(SQL_INSERT);
            
            database.getPs().setString(1, data.getLibelleClasse());
            database.getPs().setInt(2, data.getSalle().getId());
            database.getPs().setInt(3, data.getCour().getId());
            int affectedRows = database.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("L'insertion de la classe a échoué.");
                return -1;
            }
            ResultSet generatedKeys = database.getPs().getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); // retourne l'ID généré de la classe
            } else {
                throw new SQLException("La création de la classe a échoué, aucun ID obtenu.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insertion de la classe : " + e.getMessage());
        }
        finally {
            database.closeConnexion();
        }
    } else {
        // Si l'ID est déjà spécifié, utilisez la méthode update pour mettre à jour la classe existante
        update(data);
    }
    return data.getId();
}


@Override
public void update(Classe data) {
    try {
        String driver = ReadFileUtils.getDriver();
        String url = ReadFileUtils.getUrl();
        String username = ReadFileUtils.getUsername();
        String password = ReadFileUtils.getPassword();

        database.openConnexion(driver, url, username, password);
        String SQL_UPDATE = "UPDATE Classe SET libelleClasse = ?, salle_id = ?, cour_id = ? WHERE id = ?";
        database.initPreparedStatement(SQL_UPDATE);
        database.getPs().setString(1, data.getLibelleClasse());
        database.getPs().setInt(2, data.getSalle().getId());
        database.getPs().setInt(3, data.getCour().getId());
        database.getPs().setInt(4, data.getId());
        int affectedRows = database.executeUpdate();
        if (affectedRows == 0) {
            System.out.println("Aucune classe n'a été mise à jour. Vérifiez l'ID de la classe.");
        } else {
            System.out.println("La classe a été mise à jour avec succès.");
        }
        database.closeConnexion();
    } catch (SQLException e) {
        System.out.println("Erreur lors de la mise à jour de la classe : " + e.getMessage());
    }
}


    @Override
    public List<Classe> findClasseById(int classeId) {
        List<Classe> classesList = new ArrayList<>();
        try {
            String driver = ReadFileUtils.getDriver();
            String url = ReadFileUtils.getUrl();
            String username = ReadFileUtils.getUsername();
            String password = ReadFileUtils.getPassword();
    
            database.openConnexion(driver, url, username, password);
            database.initPreparedStatement(SQL_SELECT_BY);
            database.getPs().setInt(1, classeId);
            ResultSet resultSet = database.executeSelect();

            while (resultSet.next()) {
                int salleId = resultSet.getInt("salle_id");
                int courId = resultSet.getInt("cour_id");
                
                Salle salle = salleRepository.findById(salleId);
                Cour cour = courRepository.findById(courId);

                Classe classe = new Classe(
                resultSet.getInt("id"),
                resultSet.getString("libelleClasse"),
                salle,
                cour
            );
            classesList.add(classe);
            
        }
        resultSet.close();
        database.closeConnexion();
    } catch (SQLException e) {
        System.out.println("Erreur d'exécution de la requête");
    }
    return classesList.isEmpty() ? null : classesList;
    }

    @Override
    public List<Classe> listerClasses() {
    List<Classe> classesList = new ArrayList<>();
    try {
        String driver = ReadFileUtils.getDriver();
        String url = ReadFileUtils.getUrl();
        String username = ReadFileUtils.getUsername();
        String password = ReadFileUtils.getPassword();

        database.openConnexion(driver, url, username, password);
        database.initPreparedStatement(SQL_SELECT_ALL);
        ResultSet resultSet = database.executeSelect();

        while (resultSet.next()) {
            int classeId = resultSet.getInt("id");
            String libelleClasse = resultSet.getString("libelleClasse");
            int salleId = resultSet.getInt("salle_id");
            int courId = resultSet.getInt("cour_id");
            
            Salle salle = salleRepository.findById(salleId);
            Cour cour = courRepository.findById(courId);

            Classe classe = new Classe(classeId, libelleClasse, salle, cour);
            classesList.add(classe);
        }
        resultSet.close();
        database.closeConnexion();
    } catch (SQLException e) {
        System.out.println("Erreur d'exécution de la requête");
    }
    return classesList;
}

    @Override
    public Classe findById(int id) {
        Classe classe=null;
         try {  
                String driver = ReadFileUtils.getDriver();
                String url = ReadFileUtils.getUrl();
                String username = ReadFileUtils.getUsername();
                String password = ReadFileUtils.getPassword();

                database.openConnexion(driver, url,username,password);
                database.initPreparedStatement(SQL_SELECT_BY);
                database.getPs().setInt(1,id);
                ResultSet resultSet=database.executeSelect();
                if (resultSet.next()) {
                    int salleId = resultSet.getInt("salle_id");
                    int courId = resultSet.getInt("cour_id");

                    Salle salle = salleRepository.findById(salleId);
                    Cour cour = courRepository.findById(courId);

                        classe=new Classe(
                            resultSet.getInt("id"),
                            resultSet.getString("libelleClasse"),
                            salle,
                            cour
                        );
                        
                    }
                resultSet.close();
                database.closeConnexion();
            } catch (SQLException e) {
                System.out.println("Erreur execution de Requete");
            }
            return classe;
    }
  


    
    
}
