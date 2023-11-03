package org.nouha.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//eviter la redondance
public class DatabaseUtils {
    public static Connection openDatabaseConnection(String driver, String url, String username, String password) {
        Connection conn = null;
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/application.properties"));

            String loadedDriver = properties.getProperty("spring.jpa.properties.hibernate.dialect");
            String loadedUrl = properties.getProperty("spring.datasource.url");
            String loadedUsername = properties.getProperty("spring.datasource.username");
            String loadedPassword = properties.getProperty("spring.datasource.password");

            Class.forName(loadedDriver);
            conn = DriverManager.getConnection(loadedUrl, loadedUsername, loadedPassword);
        } catch (IOException e) {
            System.out.println("Erreur de chargement du fichier properties");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement du Driver");
        } catch (SQLException e) {
            System.out.println("Erreur d'ouverture de la connexion à la base de données");
        }
        return conn;
    }
}
