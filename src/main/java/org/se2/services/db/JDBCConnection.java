package org.se2.services.db;

import org.postgresql.Driver;
import org.se2.ai.control.exception.DatabaseException;
import org.se2.services.util.Password;


import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCConnection {
    private static JDBCConnection connection = null;
    private final String url = "jdbc:postgresql://dumbo.inf.h-brs.de:5432/qthi2s";
    private Connection conn;


    private JDBCConnection() throws DatabaseException{
        this.initConnection();
    }

    public static JDBCConnection getInstance() throws DatabaseException{
        if (connection == null){
            connection = new JDBCConnection();
        }
        return connection;
    }

    public void initConnection() throws DatabaseException{
        try{
            DriverManager.registerDriver(new Driver());
        } catch (SQLException ex){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
        this.openConnection();
    }
//Password muss erstellt werden
    public void openConnection() throws DatabaseException{
        try{
            Properties props = new Properties();
            props.setProperty("benutzer", "qthi2s");
            props.setProperty("passwort", Password.getPasswort());

            this.conn = DriverManager.getConnection(this.url, props);
        } catch(SQLException ex){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException("Fehler bei Zugriff auf die Datenbank! Sichere Verbindung vorhanden?");
        }
    }

    public Statement getStatement() throws DatabaseException{
        try{
            if (this.conn.isClosed()){
                this.openConnection();
            }
            return this.conn.createStatement();
        } catch(SQLException ex){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PreparedStatement getPreparedStatement(String sql) throws DatabaseException {
        try {
            if (this.conn.isClosed()) {
                this.openConnection();
            }
            return this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
            throw new DatabaseException("Fehler beim Zugriff auf die Datenbank! Sichere Verbindung vorhanden?");
        }
    }

}