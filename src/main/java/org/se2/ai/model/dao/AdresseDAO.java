package org.se2.ai.model.dao;

import org.se2.ai.model.DTO.Adresse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zmorin2s
 */

public class AdresseDAO extends AbstractDAO {
    private static AdresseDAO dao = null;

    private AdresseDAO() {

    }

    /**
     * singleton instance
     *
     * @return a static AdresseDAO instance
     */
    public static AdresseDAO getInstance() {
        if (dao == null) {
            dao = new AdresseDAO();
        }
        return dao;
    }

    /**
     * Create a new address in the database
     *
     * @param adr    - Adresse object
     * @param userId - userID of current user
     */

    public void addAdresse(Adresse adr, int userId) {
        String sql = "INSERT INTO mmuel72s.adresse (benutzer_id, strasse, hausnummer, plz, ort) values(?,?,?,?,?);";

        try (PreparedStatement statement = this.getPreparedStatement(sql)) {
            statement.setInt(1, userId);
            statement.setString(2, adr.getStrasse());
            statement.setString(3, adr.getHausnummer());
            statement.setInt(4, adr.getPlz());
            statement.setString(5, adr.getOrt());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdresseDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }



    /**
     * Get adress using benuterId
     *
     * @param benutzerId user-id of user
     * @return Adresse object
     */
    public Adresse getAdresse(String benutzerId) {
        String sql = "SELECT * "
                + "FROM mmuel72s.adresse "
                + "WHERE mmuel72s.adresse.benutzer_id = ?";
        ResultSet set = null;
        try (PreparedStatement statement = this.getPreparedStatement(sql)) {
            statement.setString(1, benutzerId);
            set = statement.executeQuery();
            if (set.next()) {
                Adresse a = new Adresse();
                a.setAdresseID(set.getInt(1));
                a.setStrasse(set.getString(2));
                a.setHausnummer(set.getString(3));
                a.setPlz(set.getInt(4));
                a.setOrt(set.getString(5));

                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VertrieblerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultset(set);
        }

        return new Adresse();
    }

    public boolean updateAdresse(Adresse adresse) {
        String sqlArbeitgeber = "UPDATE mmuel72s.adresse " +
                "SET strasse = ? " +
                ", hausnummer = ? " +
                ", plz = ? " +
                ", ort = ? " +
                "WHERE adresse_id = ?";

        try (PreparedStatement stmt = this.getPreparedStatement(sqlArbeitgeber)) {
            stmt.setString(1, adresse.getStrasse());
            stmt.setString(2, adresse.getHausnummer());
            stmt.setInt(3, adresse.getPlz());
            stmt.setString(4, adresse.getOrt());
            stmt.setInt(5, adresse.getAdresseID());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(VertrieblerDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }
}

