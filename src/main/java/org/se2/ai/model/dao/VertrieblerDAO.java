package org.se2.ai.model.dao;

import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.model.DTO.VertrieblerDTO;
import org.se2.ai.model.entities.Benutzer;
import org.se2.ai.model.entities.Vertriebler;
import org.se2.gui.ui.MyUI;
import org.se2.services.db.JDBCConnection;
import com.vaadin.ui.UI;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author WINDOWS
 */
public class VertrieblerDAO extends AbstractDAO {
    private static VertrieblerDAO dao = null;

    private final Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();

    private VertrieblerDAO() {

    }

    public static VertrieblerDAO getInstance() {
        if (dao == null) {
            dao = new VertrieblerDAO();
        }
        return dao;
    }

    public void insertVertriebler(VertrieblerDTO vertriebler) {
        int userid = user.getId();
        newVertriebler(vertriebler, userid);
    }

    private void newVertriebler(VertrieblerDTO v, int userId) {
        String newVertriebler = "INSERT INTO mmuel72s.vertriebler(vorname,name,benutzer_id) VALUES(?,?,?);";
        PreparedStatement stmt = this.getPreparedStatement(newVertriebler);
        try {
            stmt.setString(1, v.getVorname());
            stmt.setString(2, v.getName());
            stmt.setInt(3, userId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VertrieblerDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    //Methode bearbeiten! Datenbankkonform sein

    public Vertriebler getVertriebler(int benutzerid) {
        ResultSet set = null;
        String vertrieblerQuery = "SELECT * "
                + "FROM mmuel72s.vertriebler "
                + "WHERE mmuel72s.vertriebler.benutzer_id = ?";
        try (PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(vertrieblerQuery)) {
            statement.setInt(1, benutzerid);
            set = statement.executeQuery();

            if (set.next()) {
                Vertriebler a = new Vertriebler();
                a.setVertrieblerID(set.getInt(1));
                //a.setUnternehmen(set.getString(2));
                a.setId(benutzerid);
                //a.setLogo(set.getByte(4));
                //a.setBeschreibung(set.getString(5));
                return a;
            }
        } catch (SQLException | DatabaseException ex) {
            Logger.getLogger(VertrieblerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultset(set);
        }

        return null;
    }

    public Vertriebler getVertriebler(String email) {
        ResultSet set = null;
        String arbeitgeberQuery = "SELECT * \n" +
                "FROM mmuel72s.vertriebler a\n" +
                "JOIN mmuel72s.benutzer b ON  a.benutzer_id = b.benutzer_id\n" +
                "WHERE b.email = ?;";
        try (PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(arbeitgeberQuery)) {
            statement.setString(1, email);
            set = statement.executeQuery();

            if (set.next()) {
                Vertriebler a = new Vertriebler();
                a.setVertrieblerID(set.getInt(1));
                a.setName(set.getString(2));
                a.setId(set.getInt(3));
                //a.setBeschreibung(set.getString(5));
                a.setTelefonnummer(set.getString(7));
                a.setAnrede(set.getString(8));
                a.setEmail(email);
                a.setRolle(set.getString(11));
                return a;
            }
        } catch (SQLException | DatabaseException ex) {
            Logger.getLogger(VertrieblerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultset(set);
        }

        return null;
    }


    public Vertriebler getVertrieblerFromVertrieblerid(int vertrieblerId) {
        ResultSet set = null;
        String vertrieblerQuery = "SELECT * \n" +
                "FROM mmuel72s.vertriebler \n" +
                "WHERE vertriebler_id = ?;";
        try (PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(vertrieblerQuery)) {
            statement.setInt(1, vertrieblerId);
            set = statement.executeQuery();

            if (set.next()) {
                Vertriebler a = new Vertriebler();
                a.setVertrieblerID(set.getInt(1));
                a.setName(set.getString(2));
                a.setId(set.getInt(3));
                a.setVorname(set.getString(4));
                return a;
            }
        } catch (SQLException | DatabaseException ex) {
            Logger.getLogger(VertrieblerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultset(set);
        }

        return null;
    }

    public boolean updateVertriebler(VertrieblerDTO vertriebler) {
        String sqlVertriebler = "UPDATE mmuel72s.vertriebler " +
                "SET name = ? " +
                ", vorname = ? " +
                "WHERE vertriebler_id = ?";
        try (PreparedStatement stmt = this.getPreparedStatement(sqlVertriebler)) {
            stmt.setString(1, vertriebler.getName());
            stmt.setString(2, vertriebler.getVorname());
            stmt.setInt(3, vertriebler.getVertrieblerID());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(VertrieblerDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }

}






