package org.nouha.repositories.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.nouha.repositories.Database;
import org.nouha.utils.DatabaseUtils;

public class MysqlImplement implements Database {
    private  Connection conn=null;
    private PreparedStatement ps=null;

    @Override
    public PreparedStatement getPs() {
        return ps;
    }

    @Override
    public void openConnexion(String driver, String url, String username, String password) {
        this.conn = DatabaseUtils.openDatabaseConnection(driver, url, username, password);

    }

    @Override
    public void closeConnexion() {
        if (conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Erreur de fermeture de BD ");
            }
        } else {

        }
    }

    @Override
    public ResultSet executeSelect() {
        ResultSet rs=null;
        try {
            rs=ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erreur Execution de la requete");
        }
        return rs;
    }

    @Override
    public int executeUpdate() {
        int nbreLigne=0;
        try {
            nbreLigne=ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur Execution de la requete");
        }
        return nbreLigne;
    }

    @Override
    public void initPreparedStatement(String sql) {
        try {
            ps=conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
