package org.nouha.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.nouha.entities.Classe;
import org.nouha.entities.Cour;
import org.nouha.entities.Modules;
import org.nouha.repositories.ClasseRepository;
import org.nouha.repositories.CourRepository;
import org.nouha.repositories.Database;
import org.nouha.repositories.ModulesRepository;
import org.nouha.utils.ReadFileUtils;


public class ModulesRepositoryImpl implements ModulesRepository {

    private final String SQL_INSERT="INSERT INTO `Modules` (`libelleModule`, `cour_id`,classe_id) VALUES (?,?,?)";
    private final String  SQL_SELECT_ALL="SELECT * FROM Modules";
    private final String  SQL_SELECT_BY="SELECT id,libelle FROM `Modules` where id=?";
    
    private Database database;
    private ClasseRepository classeRepository;
    private CourRepository courRepository;

    //injection de dependance
    public ModulesRepositoryImpl(Database database, ClasseRepository classeRepository,CourRepository courRepository) {
        this.database = database;
        this.classeRepository = classeRepository;
        this.courRepository = courRepository;
    }

    
    




    @Override
    public List<Modules> findModuleByClasse(Classe classe) {
        ArrayList<Modules> datas = new ArrayList<>();
        try {
                    //recuperer les infos du fichier
                    String driver = ReadFileUtils.getDriver();
                    String url = ReadFileUtils.getUrl();
                    String username = ReadFileUtils.getUsername();
                    String password = ReadFileUtils.getPassword();

                    database.openConnexion(driver, url,username,password);
                    database.initPreparedStatement(SQL_SELECT_ALL);
                    database.getPs().setInt(1, classe.getId());

                    ResultSet resultSet=database.executeSelect();
                    while (resultSet.next()) {
                        Modules module=new Modules(
                            resultSet.getInt("id"),
                            resultSet.getString("libelleModule"),
                            resultSet.getInt("classe_id"),
                            resultSet.getInt("cour_id")
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
    public List<Modules> findAll() {
        ArrayList<Modules> datas=new ArrayList<>();
                  try {
                    //recuperer les infos du fichier
                    String driver = ReadFileUtils.getDriver();
                    String url = ReadFileUtils.getUrl();
                    String username = ReadFileUtils.getUsername();
                    String password = ReadFileUtils.getPassword();

                database.openConnexion(driver, url,username,password);
                        database.initPreparedStatement(SQL_SELECT_ALL);
                    ResultSet resultSet=database.executeSelect();
                    while (resultSet.next()) {
                        Modules module=new Modules(
                            resultSet.getInt("id"),
                            resultSet.getString("libelleModule"),
                            resultSet.getInt("classe_id"),
                            resultSet.getInt("cour_id")
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
                            resultSet.getString("libelleModule"),
                            resultSet.getInt("classe_id"),
                            resultSet.getInt("cour_id")
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
    public List<Modules> listerModules(Modules modules) {
        List<Modules> modulesList = new ArrayList<>();
        try {
        String driver = ReadFileUtils.getDriver();
        String url = ReadFileUtils.getUrl();
        String username = ReadFileUtils.getUsername();
        String password = ReadFileUtils.getPassword();

        database.openConnexion(driver, url, username, password);
        database.initPreparedStatement(SQL_SELECT_ALL);
        ResultSet resultSet = database.executeSelect();

        while (resultSet.next()) {
            int moduleId = resultSet.getInt("id");
            String libelleModule = resultSet.getString("libelleModule");

            int classeId = resultSet.getInt("classe_id");
            int courId = resultSet.getInt("cour_id");
            
            Classe classe = classeRepository.findById(classeId);
            Cour cour = courRepository.findById(courId);
            //classeId, libelleModule, classe, cour
            Modules module = new Modules(moduleId, libelleModule, classe, cour);
            modulesList.add(module);
        }
            resultSet.close();
            database.closeConnexion();
        } catch (SQLException e) {
            System.out.println("Erreur d'exécution de la requête");
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
        String SQL_SELECT_MODULES_BY_CLASSE = "SELECT * FROM Modules WHERE classe_id=?";
        database.initPreparedStatement(SQL_SELECT_MODULES_BY_CLASSE);
        database.getPs().setInt(1, classe.getId());
        ResultSet resultSet = database.executeSelect();

        while (resultSet.next()) {
            int moduleId = resultSet.getInt("id");
            String libelleModule = resultSet.getString("libelleModule");
            int courId = resultSet.getInt("cour_id");
            Cour cour = courRepository.findById(courId);

            Modules module = new Modules(moduleId, libelleModule, classe, cour);
            modulesList.add(module);
        }
        resultSet.close();
        database.closeConnexion();
    } catch (SQLException e) {
        System.out.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
    }
        return modulesList;
    }







    @Override
public int insert(Modules data) {
    int generatedId = 0;
    try {
        String driver = ReadFileUtils.getDriver();
        String url = ReadFileUtils.getUrl();
        String username = ReadFileUtils.getUsername();
        String password = ReadFileUtils.getPassword();

        database.openConnexion(driver, url, username, password);
        database.initPreparedStatement(SQL_INSERT);
        database.getPs().setString(1, data.getLibelleModule());
        database.getPs().setInt(2, data.getCours().getId());
        database.getPs().setInt(3, data.getClasses().getId());

        generatedId = database.executeUpdate();
        database.closeConnexion();
    } catch (SQLException e) {
        System.out.println("Erreur lors de l'exécution de la requête d'insertion : " + e.getMessage());
    }
    return generatedId;
}

    

}
