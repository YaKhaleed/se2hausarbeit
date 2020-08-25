package org.se2.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dtos.UnternehmenDTO;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.db.JDBCConnection;
import com.vaadin.ui.UI;
import org.se2.ai.model.DTO.VertrieblerDTO;
import org.se2.ai.model.entities.Benutzer;

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
        String newVertriebler = "INSERT INTO mmuel72s.vertriebler(unternehmen,benutzer_id) VALUES(?,?);";
        PreparedStatement stmt = this.getPreparedStatement(newVertriebler);
        try {
            stmt.setString(1, v.getUnternehmen());
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VertrieblerDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    public Arbeitgeber getArbeitgeber(int benutzerid) {
        ResultSet set = null;
        String arbeitgeberQuery = "SELECT * "
                + "FROM stealthyalda.arbeitgeber "
                + "WHERE stealthyalda.arbeitgeber.benutzer_id = ?";
        try (PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(arbeitgeberQuery)) {
            statement.setInt(1, benutzerid);
            set = statement.executeQuery();

            if (set.next()) {
                Arbeitgeber a = new Arbeitgeber();
                a.setArbeitgeberId(set.getInt(1));
                a.setUnternehmen(set.getString(2));
                a.setId(benutzerid);
                a.setLogo(set.getByte(4));
                a.setBeschreibung(set.getString(5));
                return a;
            }
        } catch (SQLException | DatabaseException ex) {
            Logger.getLogger(VertrieblerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultset(set);
        }

        return null;
    }

    public Arbeitgeber getArbeitgeber(String email) {
        ResultSet set = null;
        String arbeitgeberQuery = "SELECT * \n" +
                "FROM stealthyalda.arbeitgeber a\n" +
                "JOIN stealthyalda.benutzer b ON  a.benutzer_id = b.benutzer_id\n" +
                "WHERE b.email = ?;";
        try (PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(arbeitgeberQuery)) {
            statement.setString(1, email);
            set = statement.executeQuery();

            if (set.next()) {
                Arbeitgeber a = new Arbeitgeber();
                a.setArbeitgeberId(set.getInt(1));
                a.setUnternehmen(set.getString(2));
                a.setId(set.getInt(3));
                a.setBeschreibung(set.getString(5));
                a.setTelefonnummer(set.getString(7));
                a.setAnrede(set.getString(8));
                a.setEmail(email);
                a.setRole(set.getString(11));
                return a;
            }
        } catch (SQLException | DatabaseException ex) {
            Logger.getLogger(VertrieblerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultset(set);
        }

        return null;
    }

    public Arbeitgeber getArbeitgeberFromArbeitgeberid(int arbeitgeberId) {
        ResultSet set = null;
        String arbeitgeberQuery = "SELECT * \n" +
                "FROM stealthyalda.arbeitgeber \n" +
                "WHERE arbeitgeber_id = ?;";
        try (PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(arbeitgeberQuery)) {
            statement.setInt(1, arbeitgeberId);
            set = statement.executeQuery();

            if (set.next()) {
                Arbeitgeber a = new Arbeitgeber();
                a.setArbeitgeberId(set.getInt(1));
                a.setUnternehmen(set.getString(2));
                a.setId(set.getInt(3));
                a.setBeschreibung(set.getString(5));
                return a;
            }
        } catch (SQLException | DatabaseException ex) {
            Logger.getLogger(VertrieblerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultset(set);
        }

        return null;
    }

    public boolean updateArbeitgeber(UnternehmenDTO unternehmen) {
        String sqlArbeitgeber = "UPDATE stealthyalda.arbeitgeber " +
                "SET unternehmen = ? " +
                ", beschreibung = ? " +
                "WHERE arbeitgeber_id = ?";
        try (PreparedStatement stmt = this.getPreparedStatement(sqlArbeitgeber)) {
            stmt.setString(1, unternehmen.getUnternehmen());
            stmt.setString(2, unternehmen.getBeschreibung());
            stmt.setInt(3, unternehmen.getArbeitgeberId());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(VertrieblerDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }

}






