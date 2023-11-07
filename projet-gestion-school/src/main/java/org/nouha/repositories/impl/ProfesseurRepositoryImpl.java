package org.nouha.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.nouha.utils.ReadFileUtils;
import org.nouha.repositories.CourRepository;
import org.nouha.repositories.Database;
import org.nouha.repositories.ModulesRepository;
import org.nouha.entities.Cour;
import org.nouha.entities.Modules;
import org.nouha.entities.Professeur;
import org.nouha.entities.ENUM.TypeMatiereEnseigner;
import org.nouha.repositories.ProfesseurRepository;
import org.nouha.services.ProfesseurService;


public class ProfesseurRepositoryImpl implements ProfesseurRepository {
    private final String SQL_INSERT = "INSERT INTO Professeur(nomComplet, matiereEnseigner) VALUES (?, ?)";
    private final String SQL_SELECT_BY = "SELECT id, nomComplet, matiereEnseigner, isArchived FROM `Professeur` WHERE id=?";

    private Database database;
    //ProfesseurRepository professeurRepository;
    //CourRepository courRepository;
    //ModulesRepository modulesRepository;
    
    //injection
  

   
    public ProfesseurRepositoryImpl(Database database) {
        this.database = database;
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
        int lastInsertId = 0;
                try {
                    String driver = ReadFileUtils.getDriver();
                    String url = ReadFileUtils.getUrl();
                    String username = ReadFileUtils.getUsername();
                    String password = ReadFileUtils.getPassword();
                    
                    database.openConnexion(driver, url, username, password);
                    database.initPreparedStatement(SQL_INSERT);
                    database.getPs().setString(1, data.getNomComplet());
                    database.getPs().setString(2, data.getMatiereEnseigner().name());
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
    public void update(Professeur data) {
        // TODO Auto-generated method stub
        
    }

    /*@Override
    public Professeur saisirNouveauProfesseur(Scanner sc) {
        Professeur addNewProf = new Professeur();
        System.out.println("Entrer le nomComplet du prof");
        sc.nextLine(); // Ajoutez cette ligne pour consommer la nouvelle ligne
        String nomComplet = sc.nextLine();
    
        // choisir la matiere
        //professeurService.afficherOptionsMatiereEnseigner();
        System.out.println("Entrer la matiere du prof :");
        TypeMatiereEnseigner matiereEnseigner = TypeMatiereEnseigner.valueOf(sc.nextLine().toUpperCase());
        
        addNewProf.setNomComplet(nomComplet);
        addNewProf.setMatiereEnseigner(matiereEnseigner);
    
        return addNewProf;
    }*/
    

    @Override
    public Professeur findById(int id) {
        Professeur prof=null;
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
                    boolean archive = resultSet.getBoolean("isArchived");
                            prof=new Professeur(
                            resultSet.getInt("id"),
                            resultSet.getString("nomComplet"),
                            TypeMatiereEnseigner.valueOf(resultSet.getString("matiereEnseigner")),
                            archive
                        );
                        
                } 
                resultSet.close();
                database.closeConnexion();
            } catch (SQLException e) {
                System.out.println("Erreur execution de Requete");
            }
            return prof;
    }
}
