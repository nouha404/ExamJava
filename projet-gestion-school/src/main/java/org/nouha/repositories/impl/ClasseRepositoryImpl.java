package org.nouha.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.nouha.entities.Classe;
import org.nouha.entities.Modules;
import org.nouha.entities.Salle;
import org.nouha.repositories.ClasseRepository;
import org.nouha.repositories.Database;
import org.nouha.utils.ReadFileUtils;

public class ClasseRepositoryImpl implements ClasseRepository {
    private final String  SQL_SELECT_BY="SELECT * FROM `Classe` where id=?";
    private final String  SQL_SELECT_ALL="SELECT * FROM Classe";
    private final String SQL_INSERT = "INSERT INTO Classe(libelleClasse,isArchived ,salle_id) VALUES (?,?,?)";
    private final String SQL_UPDATE = "UPDATE Classe SET libelleClasse = ?, isArchived = ?, salle_id = ? WHERE id = ?";
    //private final String SQL_SELECT_BY_CLASSE= "SELECT * FROM `Classe` WHERE id = ?";
    private final String SQL_ARCHIVE = "UPDATE Classe SET isArchived = ? WHERE id = ?";
    private final String SQL_SELECT_ALL_MODULES_BY_CLASSE_ID="SELECT * FROM Classe WHERE id =?";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM Classe WHERE id = ?";

    //private final String SQL_SELECT_FROM_PROF = "SELECT * FROM Professeur WHERE id = ?";

    private Database database;

    //injection dependance
   

    
    public ClasseRepositoryImpl(Database database) {
        this.database = database;
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
                //je filtre les au lieu de le mettre dans la requette
                boolean isArchived = resultSet.getBoolean("isArchived");
                if (!isArchived) {
                    Classe classe = new Classe(
                        resultSet.getInt("id"), 
                        resultSet.getString("libelleClasse"),
                        isArchived

                        );
                    classesList.add(classe);
                }
                
               
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
        int lastInsertId = 0;
        try {
            String driver = ReadFileUtils.getDriver();
            String url = ReadFileUtils.getUrl();
            String username = ReadFileUtils.getUsername();
            String password = ReadFileUtils.getPassword();

            database.openConnexion(driver, url, username, password);
            database.initPreparedStatement(SQL_INSERT);

            database.getPs().setString(1, data.getLibelleClasse());
            database.getPs().setBoolean(2, data.isArchive());
            database.getPs().setInt(3, data.getSalles().getId());

            lastInsertId = database.executeUpdate();
            database.closeConnexion();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'exécution de la requête d'insertion : " + e.getMessage());
        } 
        return lastInsertId;
    }

   


@Override
public void update(Classe data) {
    try {
        String driver = ReadFileUtils.getDriver();
        String url = ReadFileUtils.getUrl();
        String username = ReadFileUtils.getUsername();
        String password = ReadFileUtils.getPassword();

        database.openConnexion(driver, url, username, password);
        
        database.initPreparedStatement(SQL_UPDATE);
        
        database.getPs().setString(1, data.getLibelleClasse());
        database.getPs().setBoolean(2, data.isArchive());
        database.getPs().setInt(3, data.getSalles().getId());
        database.getPs().setInt(5, data.getId());

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
    public List<Modules> findModuleByID(int classeId) {
        List<Modules> classesList = new ArrayList<>();
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
                //int salleId = resultSet.getInt("salle_id");
                //int courId = resultSet.getInt("cour_id");
                
                //Salle salle = salleRepository.findById(salleId);
                //Cour cour = courRepository.findById(courId);

                Modules classe = new Modules(
                resultSet.getInt("id"),
                resultSet.getString("libelleClasse"),
                resultSet.getBoolean("isArchived"),
                null,
                null
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
                    boolean archive = resultSet.getBoolean("isArchived");
                        classe=new Classe(
                            resultSet.getInt("id"),
                            resultSet.getString("libelleClasse"),
                            archive,
                            null
                        );
                        
                    }
                resultSet.close();
                database.closeConnexion();
            } catch (SQLException e) {
                System.out.println("Erreur execution de Requetessss");
            }
            return classe;
    }

    @Override
    public List<Classe> findOneClasse(Classe classe) {
        List<Classe> classList = new ArrayList<>();
        try {
            
            String driver = ReadFileUtils.getDriver();
            String url = ReadFileUtils.getUrl();
            String username = ReadFileUtils.getUsername();
            String password = ReadFileUtils.getPassword();

            database.openConnexion(driver, url, username, password);
            database.initPreparedStatement(SQL_SELECT_BY_ID);
            database.getPs().setInt(1, classe.getId());
            ResultSet resultSet = database.executeSelect();
            while (resultSet.next()) {
                Classe classes = new Classe();
                classes.setId(resultSet.getInt("id"));
                classes.setLibelleClasse(resultSet.getString("libelleClasse"));
                classes.setArchive(resultSet.getBoolean("isArchived"));
               
                classList.add(classes);
            }
            resultSet.close();
            database.closeConnexion();
        } catch (SQLException e) {
            System.out.println("Erreur d'exécution de la requête "+ e.getMessage());
        }
        return classList;
    } 


       @Override
        public boolean modifierClasse(Classe classe) {
        try {
            String driver = ReadFileUtils.getDriver();
            String url = ReadFileUtils.getUrl();
            String username = ReadFileUtils.getUsername();
            String password = ReadFileUtils.getPassword();

            database.openConnexion(driver, url, username, password);
            database.initPreparedStatement(SQL_UPDATE);

            database.getPs().setString(1, classe.getLibelleClasse());
            database.getPs().setBoolean(2, classe.isArchive());
            database.getPs().setInt(3, classe.getSalles().getId());
            database.getPs().setInt(4, classe.getId());

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
    public boolean archiverClasse(int id) {
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




    /*@Override
    public List<Classe> findOneClasse(Classe classe) {
        List<Classe> ClasseList = new ArrayList<>();
        try {
            String driver = ReadFileUtils.getDriver();
            String url = ReadFileUtils.getUrl();
            String username = ReadFileUtils.getUsername();
            String password = ReadFileUtils.getPassword();

            database.openConnexion(driver, url, username, password);
            database.initPreparedStatement(SQL_SELECT_BY_CLASSE);
            database.getPs().setInt(1, classe.getId());
            ResultSet resultSet = database.executeSelect();
            while (resultSet.next()) {
                Classe classes = new Classe();

                classes.setId(resultSet.getInt("id"));
                classes.setLibelleClasse(resultSet.getString("libelleClasse"));
                int salleId = resultSet.getInt("salle_id");
                int courId = resultSet.getInt("cour_id");
                Salle salle = salleRepository.findById(salleId);
                Cour cour = courRepository.findById(courId);

                classes.setSalle(salle);
                classes.setCour(cour);
                classes.setArchive(resultSet.getBoolean("archive"));
                
                ClasseList.add(classes);
            }
            resultSet.close();
            database.closeConnexion();
        } catch (SQLException e) {
            System.out.println("Erreur d'exécution de la requête");
        }
        return ClasseList;
    }*/
  

    @Override
    public List<Classe> getclassList(Classe classe) {
        List<Classe> classesList = new ArrayList<>();
        try {
            String driver = ReadFileUtils.getDriver();
            String url = ReadFileUtils.getUrl();
            String username = ReadFileUtils.getUsername();
            String password = ReadFileUtils.getPassword();

            database.openConnexion(driver, url, username, password);
            database.initPreparedStatement(SQL_SELECT_ALL_MODULES_BY_CLASSE_ID);
            database.getPs().setInt(1, classe.getId());

            ResultSet resultSet = database.executeSelect();
        while (resultSet.next()) {
            //les données qui m'interessent
            int classeID = resultSet.getInt("id"); 
            String libelleClasse = resultSet.getString("libelleClasse");
            boolean archive = resultSet.getBoolean("isArchived");

            Classe classe2 = new Classe(
                classeID,
                libelleClasse,
                archive
            );
            
            classesList.add(classe2);
        }
            resultSet.close();
            database.closeConnexion();
        } catch (SQLException e) {
            System.out.println("Erreur d'exécution de la requête "+ e.getMessage());
        }
        return classesList;
    }

    /*@Override
    public List<Modules> findModuleByClasseID(int classeId) {
        List<Modules> modulesList = new ArrayList<>();
        try {
        String driver = ReadFileUtils.getDriver();
        String url = ReadFileUtils.getUrl();
        String username = ReadFileUtils.getUsername();
        String password = ReadFileUtils.getPassword();

        database.openConnexion(driver, url, username, password);
        database.initPreparedStatement(SQL_SELECT_ALL_MODULES_BY_CLASSE_ID);
        database.getPs().setInt(1, classeId);

        ResultSet resultSet = database.executeSelect();
        while (resultSet.next()) {
            //les données qui m'interessent
            int moduleId = resultSet.getInt("id"); 
            String libelleModule = resultSet.getString("libelleModule");
            boolean archive = resultSet.getBoolean("archive");
         
            Modules module = new Modules(moduleId, libelleModule, archive); 
           
            modulesList.add(module);
        }
            resultSet.close();
            database.closeConnexion();
        } catch (SQLException e) {
            System.out.println("Erreur d'exécution de la requête");
        }
        
        return modulesList;
    }*/

    /*@Override
    public Professeur findProfByCourID(int courID) {
        Professeur prof=null;
         try {  
                String driver = ReadFileUtils.getDriver();
                String url = ReadFileUtils.getUrl();
                String username = ReadFileUtils.getUsername();
                String password = ReadFileUtils.getPassword();

                database.openConnexion(driver, url,username,password);
                database.initPreparedStatement(SQL_SELECT_FROM_PROF);
                database.getPs().setInt(1,courID);
                ResultSet resultSet=database.executeSelect();
                if (resultSet.next()) {
                    boolean archive = resultSet.getBoolean("archive");
                    int profId = resultSet.getInt("id");
                    List<Cour> cours = courRepository.findCourById(profId);
                    
                    //je veux afficher le libelle de la classe, le nomComplet du professeur, matiereEnseigner , archive et le nom du cours
                    prof= new Professeur(profId, resultSet.getString("nomComplet"), TypeMatiereEnseigner.valueOf(resultSet.getString("matiereEnseigner")), archive,cours);
                    
                    }
                resultSet.close();
                database.closeConnexion();
            } catch (SQLException e) {
                System.out.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur de conversion de la chaîne en enum : " + e.getMessage());
            }
            return prof;
    }*/

   

    
     
    
}
