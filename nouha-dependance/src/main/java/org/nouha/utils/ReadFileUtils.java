package org.nouha.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ReadFileUtils {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
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
