package org.nouha.views;

import org.nouha.repositories.impl.MysqlImplement;
import org.nouha.repositories.Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get("src/main/resources/application.properties")));

            String driver = properties.getProperty("spring.jpa.properties.hibernate.dialect");
            String url = properties.getProperty("spring.datasource.url");
            String username = properties.getProperty("spring.datasource.username");
            String password = properties.getProperty("spring.datasource.password");

            System.out.println("Driver: " + driver);
            System.out.println("URL: " + url);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);

            Database db = new MysqlImplement();
            db.openConnexion(driver, url, username, password);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}