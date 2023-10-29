package org.nouha.repositories;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface Database {
    void openConnexion(String driver, String url, String username, String password);
    void closeConnexion();
    ResultSet executeSelect();
    int executeUpdate();
    PreparedStatement getPs();
    void initPreparedStatement(String sql);
}