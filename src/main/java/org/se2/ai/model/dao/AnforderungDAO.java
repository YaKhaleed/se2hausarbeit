package org.se2.ai.model.dao;

import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.model.DTO.AnforderungAutoanzeige;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AnforderungDAO extends AbstractDAO {
    private static AnforderungDAO dao;

    private AnforderungDAO() {

    }

    public static AnforderungDAO getInstance() {
        if (dao == null) {
            dao = new AnforderungDAO();
        }
        return dao;

    }

    /**
     * @return list of Stellenanzeigen
     * @throws DatabaseException if something goes horribly wrong
     */
    /*
    public List<Anforderung> getAnforderungForStellenanzeige(int stellenanzeigeid) throws DatabaseException {
        ResultSet rs = null;
        List<Anforderung> liste = new ArrayList<>();
        Anforderung anforderung = null;
        try (PreparedStatement stmt = getPreparedStatement(
                "SELECT anforderung FROM stealthyalda.anforderung WHERE stealthyalda.anforderung.stellenanzeige_id = ?")) {
            stmt.setInt(1, stellenanzeigeid);
            rs = stmt.executeQuery();

            while (rs.next()) {
                anforderung = new Anforderung();
                anforderung.setAnforderung(rs.getString(1));
                liste.add(anforderung);
            }
        } catch (SQLException throwables) {
            logEntry(this.getClass().getName(), Level.SEVERE, throwables.getMessage());
            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        } finally {
            closeResultset(rs);
        }

        return liste;
    }

     */

    public boolean createAnforderung(int arbeitgeberId, String anforderung) {
        String sql = "insert into mmuel72s.anforderung values(?,?);";
        PreparedStatement statement = this.getPreparedStatement(sql);

        //Zeilenweise Abbildung der Daten auf die Spalten der erzeugten Zeile
        try {
            statement.setInt(1, arbeitgeberId);
            statement.setString(2, anforderung);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AnforderungDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
