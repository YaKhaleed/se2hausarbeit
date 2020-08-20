package org.se2.ai.model.dao;

import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.services.db.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO {

    protected static void closeResultset(ResultSet r) {
        if (r != null) {
            try {
                r.close();
            } catch (SQLException throwables) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, throwables.getMessage());
            }
        }
    }

    protected Statement getStatement() {
        Statement statement = null;
        try {
            statement = JDBCConnection.getInstance().getStatement();
        } catch (DatabaseException ex) {
            Logger.getLogger(AutoanzeigeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statement;
    }

    protected PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement statement = null;
        try {
            statement = JDBCConnection.getInstance().getPreparedStatement(sql);
        } catch (DatabaseException ex) {
            Logger.getLogger(AutoanzeigeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statement;
    }

    protected void logEntry(String className, Level level, String message) {
        Logger.getLogger(className).log(level, message);
    }

    protected int benutzerID() throws SQLException {
        Statement statement = this.getStatement();
        ResultSet rs = null;
        int currentValue = 0;

        try {
            rs = statement.executeQuery("SELECT max(mmuel72s.benutzer.benutzer_id) FROM mmuel72s.benutzer");
        } catch (SQLException ex) {
            Logger.getLogger(BenutzerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


        if (rs != null) {
            try {
                rs.next();
                currentValue = rs.getInt(1);
            } catch (SQLException ex) {
                Logger.getLogger(AdresseDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeResultset(rs);
            }
        }
        return currentValue;
    }
}

