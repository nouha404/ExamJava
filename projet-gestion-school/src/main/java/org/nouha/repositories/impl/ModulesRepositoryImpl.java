package org.nouha.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.nouha.entities.Classe;
import org.nouha.entities.Modules;
import org.nouha.entities.Professeur;
import org.nouha.repositories.ClasseRepository;
import org.nouha.repositories.Database;
import org.nouha.repositories.ModulesRepository;
import org.nouha.repositories.ProfesseurRepository;
import org.nouha.utils.ReadFileUtils;


public class ModulesRepositoryImpl implements ModulesRepository {

    private final String SQL_INSERT = "INSERT INTO Modules(libelleModule, professeur_id) VALUES (?, ?)";
    //  private final String SQL_INSERT = "INSERT INTO Salle(libelleSalle, capacite, numeroSalle, isArchived) VALUES (?, ?, ?, ?)";
    private final String  SQL_SELECT_ALL_MODULES="SELECT * FROM Modules WHERE isArchived = false";
    private final String  SQL_SELECT_BY="SELECT id, libelleModule, archive FROM `Modules` WHERE id=?";
    private final String SQL_SELECT_MODULES_BY_CLASSE = "SELECT * FROM Modules WHERE classe_id = ?";
    private final String SQL_SELECT_MODULES_BY_PROFESSEUR = "SELECT * FROM Modules WHERE professeur_id = ?";
    private final String SQL_SELECT_MODULES = "SELECT * FROM Modules WHERE module_id = ?";
    private final String SQL_SELECT_MOD_BY_PROF = "SELECT * FROM Modules WHERE professeur_id = ?";



    private Database database;
    private ClasseRepository classeRepository;
    private ProfesseurRepository professeurRepository;

    //injection de dependance
        public ModulesRepositoryImpl(Database database,ClasseRepository classeRepository,ProfesseurRepository professeurRepository) {
        this.database = database;
        this.classeRepository = classeRepository;
        this.professeurRepository = professeurRepository;
    }


    
    

    @Override
    public List<Modules> findAll() {
        ArrayList<Modules> datas=new ArrayList<>();
            try {
            //recuperer les infos du fichier
            String driver = ReadFileUtils.getDriver();
            String url = ReadFileUtils.getUrl();
            String username = ReadFileUtils.getUsername();
            String password = ReadFileUtils.getPassword();

                database.openConnexion(driver, url,username,password);
                        database.initPreparedStatement(SQL_SELECT_ALL_MODULES);
                    ResultSet resultSet=database.executeSelect();
                    while (resultSet.next()) {
                        boolean archive = resultSet.getBoolean("isArchived");
                       
                        Modules module=new Modules(
                            resultSet.getInt("id"),
                            resultSet.getString("libelleModule"),
                            archive,
                            null,
                            null
                            
                            );
                         datas.add(module);
                           
                      }
                   resultSet.close();
                   database.closeConnexion();
                } catch (SQLException e) {
                    System.out.println("Erreur execution de Requete");
                }
            
            return datas;
    }

    @Override
    public void hidden(Modules data) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(Modules data) {
        // TODO Auto-generated method stub
        
    }




    @Override
    public Modules findById(int id) {
        Modules module=null;
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
                         module=new Modules(
                            resultSet.getInt("id"),
                            resultSet.getString("libelleModule")
                            );
                           
                      }
                   resultSet.close();
                   database.closeConnexion();
                } catch (SQLException e) {
                    System.out.println("Erreur execution de Requete");
                }
            return module;
    }

    @Override
    public List<Modules> getModuleList(Modules module){
        List<Modules> modulesList = new ArrayList<>();
        try {
            String driver = ReadFileUtils.getDriver();
            String url = ReadFileUtils.getUrl();
            String username = ReadFileUtils.getUsername();
            String password = ReadFileUtils.getPassword();

            database.openConnexion(driver, url, username, password);
            database.initPreparedStatement(SQL_SELECT_MODULES);
            database.getPs().setInt(1, module.getId());
            ResultSet resultSet = database.executeSelect();
            
            while (resultSet.next()){
                int moduleId = resultSet.getInt("id");
                String libelleModule = resultSet.getString("libelleModule");
                boolean archive = resultSet.getBoolean("isArchived");
                int classeId = resultSet.getInt("classe_id");

                Classe classes = classeRepository.findById(classeId);
                List<Classe> arrayCls = classeRepository.getclassList(classes);
   
                Modules modules = new Modules(
                    moduleId,
                    libelleModule,
                    archive,
                    arrayCls,
                    null

                );
                modulesList.add(modules);
            }
            resultSet.close();
            database.closeConnexion();
        }catch (SQLException e) {
            System.out.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
        }
        return modulesList;
    }
    
    @Override
    public List<Modules> listerModulesParProfesseur(Professeur professeur) {
        List<Modules> modulesList = new ArrayList<>();
        try {
            String driver = ReadFileUtils.getDriver();
            String url = ReadFileUtils.getUrl();
            String username = ReadFileUtils.getUsername();
            String password = ReadFileUtils.getPassword();

            database.openConnexion(driver, url, username, password);
            database.initPreparedStatement(SQL_SELECT_MODULES_BY_PROFESSEUR);
            database.getPs().setInt(1, professeur.getId());
            ResultSet resultSet = database.executeSelect();

            while (resultSet.next()) {
                int moduleId = resultSet.getInt("id");
                String libelleModule = resultSet.getString("libelleModule");
                boolean archive = resultSet.getBoolean("isArchived");
                int classeId = resultSet.getInt("classe_id");
                Classe classes = classeRepository.findById(classeId);
                List<Classe> arrayCls = classeRepository.getclassList(classes);
                
                int profId = resultSet.getInt("professeur_id");
                Professeur prof = professeurRepository.findById(profId);

                Modules modules = new Modules(
                    moduleId,
                    libelleModule,
                    archive,
                    arrayCls,
                    prof

                );
                modulesList.add(modules);
            }
        resultSet.close();
        database.closeConnexion();
    } 
        catch (SQLException e) {
        System.out.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
    }
        return modulesList;
    }


    @Override
    public List<Modules> listerModulesParClasse(Classe classe) {
        List<Modules> modulesList = new ArrayList<>();
    try {
        String driver = ReadFileUtils.getDriver();
        String url = ReadFileUtils.getUrl();
        String username = ReadFileUtils.getUsername();
        String password = ReadFileUtils.getPassword();

        database.openConnexion(driver, url, username, password);
        database.initPreparedStatement(SQL_SELECT_MODULES_BY_CLASSE);
        database.getPs().setInt(1, classe.getId());
        ResultSet resultSet = database.executeSelect();

        while (resultSet.next()) {
            int moduleId = resultSet.getInt("id");
            String libelleModule = resultSet.getString("libelleModule");
            boolean archive = resultSet.getBoolean("isArchived");
            int classeId = resultSet.getInt("classe_id");
            Classe classes = classeRepository.findById(classeId);
            List<Classe> arrayCls = classeRepository.getclassList(classes);
            
            int profId = resultSet.getInt("professeur_id");
            Professeur prof = professeurRepository.findById(profId);

            Modules modules = new Modules(
                moduleId,
                libelleModule,
                archive,
                arrayCls,
                prof

            );
            modulesList.add(modules);
        }
        resultSet.close();
        database.closeConnexion();
    } catch (SQLException e) {
        System.out.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
    }
        return modulesList;
    }
    

    //marche pas
    @Override
    public int insert(Modules data) {
    int lastInsertId = 0;
    try {
        String driver = ReadFileUtils.getDriver();
        String url = ReadFileUtils.getUrl();
        String username = ReadFileUtils.getUsername();
        String password = ReadFileUtils.getPassword();

        database.openConnexion(driver, url, username, password);
        database.initPreparedStatement(SQL_INSERT);
        database.getPs().setString(1, data.getLibelleModule());
        database.getPs().setInt(2, data.getProfesseurs().getId());
   
        lastInsertId = database.executeUpdate();
        database.closeConnexion();
    } catch (SQLException e) {
        System.out.println("Erreur lors de l'exécution de la requête d'insertion : " + e.getMessage());
    }
    return lastInsertId;
    }





    @Override
public List<Modules> findModuleByProfesseur(Professeur professeur) {
    List<Modules> modulesList = new ArrayList<>();
    try {
        String driver = ReadFileUtils.getDriver();
        String url = ReadFileUtils.getUrl();
        String username = ReadFileUtils.getUsername();
        String password = ReadFileUtils.getPassword();

        database.openConnexion(driver, url, username, password);
        database.initPreparedStatement(SQL_SELECT_MOD_BY_PROF);
        database.getPs().setInt(1, professeur.getId());

        ResultSet resultSet = database.executeSelect();

        while (resultSet.next()) {

            int moduleId = resultSet.getInt("id");
            String libelleModule = resultSet.getString("libelleModule");
            boolean archive = resultSet.getBoolean("isArchived");
            int classeId = resultSet.getInt("classe_id");

            Classe classes = classeRepository.findById(classeId);
            List<Classe> arrayCls = classeRepository.getclassList(classes);

            Modules module = new Modules(
                moduleId,
                libelleModule,
                archive,
                arrayCls,
                professeur
            );
            modulesList.add(module);
        }

        resultSet.close();
        database.closeConnexion();
    } catch (SQLException e) {
        System.out.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
    }
    return modulesList;
}


    
    
}
