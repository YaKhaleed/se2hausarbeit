package org.se2.ai.model.dao;



import org.se2.ai.model.DTO.KundeDTO;
import org.se2.ai.model.entities.Benutzer;
import org.se2.ai.model.entities.Kunde;
import org.se2.gui.ui.MyUI;
import com.vaadin.ui.UI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zmorin2s
 */

public class KundeDAO extends AbstractDAO {
    private static KundeDAO dao = null;
    Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();

    private KundeDAO() {

    }

    public static KundeDAO getInstance() {
        if (dao == null) {
            dao = new KundeDAO();
        }
        return dao;
    }



    public void newKunde(KundeDTO kunde) {
        ResultSet nextKey = null;
        String sql = "INSERT INTO mmuel72s.kunde(vorname, nachname, benutzer_id) VALUES(?,?,?);";
        try (PreparedStatement stmt = this.getPreparedStatement(sql)) {
            stmt.setString(1, kunde.getVorname());
            stmt.setString(2, kunde.getNachname());
            stmt.setInt(3, user.getId());

            int rowsChanged = stmt.executeUpdate();
            if (rowsChanged == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            nextKey = stmt.getGeneratedKeys();

            if (nextKey.next()) {
                int kundenId = nextKey.getInt(1);
                kunde.setKundenID(kundenId);
                logEntry("BenutzerDAO", Level.INFO, "Found kunde_id: " + kundenId);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VertrieblerDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            closeResultset(nextKey);
        }
    }

    //Kunde über ID bekommen
    public Kunde getKunde(int benutzerid) {
        ResultSet set = null;
        try (PreparedStatement stmt = this.getPreparedStatement("SELECT * "
                + "FROM mmuel72s.kunde "
                + "WHERE mmuel72s.kunde.benutzer_id = ?")) {
            stmt.setInt(1, benutzerid);
            set = stmt.executeQuery();

            if (set.next()) {
                Kunde s = new Kunde();
                s.setKundenID(set.getInt(1));
                s.setNachname(set.getString(2));
                s.setVorname(set.getString(4));
                s.setId(benutzerid);
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VertrieblerDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            closeResultset(set);
        }
        return null;
    }


    public Kunde getKunde(String email) {
        ResultSet set = null;
        try {
            PreparedStatement stmt = this.getPreparedStatement("SELECT * \n" +
                    "FROM mmuel72s.kunde s\n" +
                    "JOIN mmuel72s.benutzer b ON  s.benutzer_id = b.benutzer_id\n" +
                    "WHERE b.email = ?;");
            stmt.setString(1, email);
            set = stmt.executeQuery();

            if (set.next()) {
                Kunde k = new Kunde();
                k.setKundenID(set.getInt(1));
                k.setNachname(set.getString(2));
                k.setId(set.getInt(3));
                k.setVorname(set.getString(4));
                k.setEmail(email);
                k.setRolle(set.getString(11));
                return k;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VertrieblerDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            closeResultset(set);
        }
        return null;
    }

}

