package org.se2.ai.model.dao;

import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.model.DTO.RolleDTO;
import org.se2.ai.model.entities.Benutzer;
import org.se2.ai.model.dao.RolleDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RolleDao extends AbstractDAO{

    private static RolleDao dao;

    private RolleDao() {

    }

    public static RolleDao getInstance() {
        if (dao == null) {
            dao = new RolleDao();
        }
        return dao;
    }

    public List<RolleDTO> getRolesForUser(Benutzer benutzer) throws SQLException, DatabaseException {
        ResultSet rs = null;
        // labs

        try {
            PreparedStatement preparedStatement = this.getPreparedStatement("SELECT role from mmuel72s.benutzer where mmuel72s.benuter.benutzer_id =  = ?;");

            preparedStatement.setInt(1, benutzer.getId());

            rs = preparedStatement.executeQuery();
            Logger.getLogger(RolleDao.class.getName()).log(Level.INFO, "all good");
        } catch (SQLException throwables) {
            logEntry(this.getClass().getName(), Level.SEVERE, throwables.getMessage());
            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        }
        // labs


        List<RolleDTO> liste = new ArrayList<>();
        RolleDTO role;

        try {
            while (rs.next()) {
                role = new RolleDTO();
                role.setBezeichnung(rs.getString(1));
                liste.add(role);
            }
        } catch (SQLException throwables) {
            logEntry(this.getClass().getName(), Level.SEVERE, throwables.getMessage());
            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");

        } finally {
            rs.close();
        }

        return liste;
    }



}
