package org.nouha.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ReadFileUtils {
    private static final Properties properties = new Properties();

    static {
        try {
            InputStream input = ReadFileUtils.class.getClassLoader().getResourceAsStream("application.properties");
            if (input == null) {
                throw new FileNotFoundException("Le fichier de propriétés 'application.properties' n'a pas été trouvé.");
            }
            properties.load(input);
            //properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDriver() {
        return properties.getProperty("spring.jpa.properties.hibernate.dialect");
    }

    public static String getUrl() {
        return properties.getProperty("spring.datasource.url");
    }

    public static String getUsername() {
        return properties.getProperty("spring.datasource.username");
    }

    public static String getPassword() {
        return properties.getProperty("spring.datasource.password");
    }
}
