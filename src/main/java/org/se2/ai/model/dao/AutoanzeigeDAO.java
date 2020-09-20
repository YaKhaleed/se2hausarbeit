package org.se2.ai.model.dao;

import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.model.DTO.AutoanzeigeDTO;
import org.se2.ai.model.entities.Autoanzeige;
import org.se2.ai.model.entities.Benutzer;
import org.se2.ai.model.entities.Vertriebler;
import org.se2.services.db.JDBCConnection;
import org.se2.services.util.Roles;
import com.vaadin.ui.UI;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zmorin2s
 */

public class AutoanzeigeDAO extends AbstractDAO {

    private static AutoanzeigeDAO dao = null;

    private AutoanzeigeDAO() {

    }

    public static AutoanzeigeDAO getInstance() {
        if (dao == null) dao = new AutoanzeigeDAO();
        return dao;

    }

    public AutoanzeigeDTO getAutoanzeige(String titel) {
        String sql = "select * from mmuel72s.autoanzeige where titel = ?";
        /*+
                "and beschreibung = ?" +
                "and ort = ?" +
                "and status = ?;";

         */
        ResultSet rs = null;
        AutoanzeigeDTO autoanzeige = new AutoanzeigeDTO();
        try {
            // use prepared stmt
            PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);
            statement.setString(1, titel);
            //statement.setString(2, beschreibung);
            //statement.setString(3, ort);
            //statement.setString(4, status);
            rs = statement.executeQuery();
            assert (rs != null);
            fillAutoanzeige(rs, autoanzeige, 6);
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.INFO, null, rs);
        } catch (SQLException | DatabaseException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            org.se2.ai.model.dao.AbstractDAO.closeResultset(rs);
        }
        return autoanzeige;

    }

    public List<AutoanzeigeDTO> getAutoanzeigeListe(String titel) {
        ResultSet set = null;
        List<AutoanzeigeDTO> liste = new ArrayList<>();
        String sql = "select *\n" +
                "from mmuel72s.autoanzeige s\n" +

                "where s.titel = ?";
        try {
            PreparedStatement statement = this.getPreparedStatement(sql);
            statement.setString(1, titel);
            set = statement.executeQuery();
            assert (set != null);
            while (set.next()) {
                AutoanzeigeDTO s = new AutoanzeigeDTO();
                s.setAutoanzeigenID(set.getInt(1));
                s.setTitel(set.getString(2));
                s.setBeschreibung(set.getString(3));
                s.setBaujahr(set.getInt(5));
                s.setStatus(set.getString(6));
                s.setDatum(set.getDate(7).toLocalDate());
                liste.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AutoanzeigeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            org.se2.ai.model.dao.AbstractDAO.closeResultset(set);
        }
        return liste;
    }

    //Auskommentierte Zeilen drübergehen
    private void fillAutoanzeige(ResultSet rs, AutoanzeigeDTO autoanzeige, int i) throws SQLException {
        while (rs.next()) {
            Vertriebler a;
            autoanzeige.setAutoanzeigenID(rs.getInt(1));
            autoanzeige.setTitel(rs.getString(2));
            autoanzeige.setBeschreibung(rs.getString(3));
            autoanzeige.setBaujahr(rs.getInt(5));
            autoanzeige.setStatus(rs.getString(6));
            autoanzeige.setDatum(rs.getDate(7).toLocalDate());
            a = VertrieblerDAO.getInstance().getVertrieblerFromVertrieblerid(rs.getInt(i));
            autoanzeige.setVertriebler(a);
        }
    }

    public boolean createAutoanzeige(Autoanzeige v) {

        String sql = "insert into mmuel72s.autoanzeige values(default,?,?,?,?,?,?);";
        PreparedStatement statement = this.getPreparedStatement(sql);
        Benutzer user = (Benutzer) UI.getCurrent().getSession().getAttribute(Roles.CURRENTUSER);
        String email = user.getEmail();
        Vertriebler a = VertrieblerDAO.getInstance().getVertriebler(email);

        //Zeilenweise Abbildung der Daten auf die Spalten der erzeugten Zeile
        try {
            statement.setString(1, v.getTitel());
            statement.setString(2, v.getBeschreibung());
            statement.setString(3, v.getStatus());
            statement.setDate(4, Date.valueOf(v.getDatum()));
            //statement.setInt(5, v.getVertrieblerID());
            statement.setString(6, v.getOrt());
            statement.executeUpdate();

            //Nachtragliches Setzen der BuchungsID
            setAutoanzeigeID(v);
            /*
            List<AnforderungAutoanzeige> list = v.getAutoanforderung();

            for (int i = 0; i < list.size(); i++) {
                AnforderungDAO.getInstance().createAnforderung(v.getAutoanzeigenID(), list.get(i).getAutoAnforderung());
            }
            */
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AutoanzeigeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private void setAutoanzeigeID(Autoanzeige a) throws SQLException {
        Statement statement = this.getStatement();
        ResultSet rs = null;

        int currentValue = 0;
        try {
            rs = statement.executeQuery("SELECT max(mmuel72s.autoanzeige.autoanzeige_id) FROM mmuel72s.autoanzeige");

            assert rs != null;
            rs.next();
            currentValue = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(AutoanzeigeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            AbstractDAO.closeResultset(rs);
        }

        a.setAutoanzeigenID(currentValue);
    }

    public List<AutoanzeigeDTO> getAutoanzeigeByVertriebler(String vertriebler) {
        ResultSet set = null;
        List<AutoanzeigeDTO> liste = new ArrayList<>();
        String sql = "select *\n" +
                "from mmuel72s.autoanzeige s\n" +
                "join mmuel72s.vertriebler a\n" +
                "on s.vertriebler_id = a.vertriebler_id\n" +
                "where a.name = ? order by s.autoanzeige_id";
        try {
            PreparedStatement statement = this.getPreparedStatement(sql);
            statement.setString(1, vertriebler);
            set = statement.executeQuery();
            assert (set != null);
            while (set.next()) {
                AutoanzeigeDTO s = new AutoanzeigeDTO();
                s.setAutoanzeigenID(set.getInt(1));
                s.setTitel(set.getString(2));
                s.setBeschreibung(set.getString(3));
                s.setBaujahr(set.getInt(5));
                s.setStatus(set.getString(6));
                s.setDatum(set.getDate(7).toLocalDate());
                liste.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AutoanzeigeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            org.se2.ai.model.dao.AbstractDAO.closeResultset(set);
        }
        return liste;
    }



    private List<AutoanzeigeDTO> hilfe(String sql) {
        ResultSet rs = null;
        List<AutoanzeigeDTO> liste = new ArrayList<>();
        AutoanzeigeDTO autoanzeige = null;
        try {
            // use prepared stmt
            PreparedStatement preparedStatement = JDBCConnection.getInstance().getPreparedStatement(sql);

            rs = preparedStatement.executeQuery();
            assert (rs != null);
            while (rs.next()) {
                autoanzeige = new AutoanzeigeDTO();
                autoanzeige.setTitel(rs.getString(2));
                autoanzeige.setBeschreibung(rs.getString(3));
                autoanzeige.setStatus(rs.getString(6));
                autoanzeige.setDatum(rs.getDate(7).toLocalDate());
                autoanzeige.setVertrieblerID(rs.getInt(4));
                autoanzeige.setBaujahr(rs.getInt(5));
                liste.add(autoanzeige);
            }
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.INFO, null, rs);
        } catch (SQLException | DatabaseException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            org.se2.ai.model.dao.AbstractDAO.closeResultset(rs);
        }

        return liste;
    }


    public AutoanzeigeDTO getAutoanzeigeByID(int autoanzeigeID) {
        String sql = "SELECT * \n" +
                "FROM mmuel72s.autoanzeige s\n" +
                "JOIN mmuel72s.vertriebler a\n" +
                "ON s.vertriebler_id = a.vertriebler_id\n" +
                "WHERE s.autoanzeige_id = '" + autoanzeigeID + "';";
        ResultSet rs = null;
        AutoanzeigeDTO autoanzeige = new AutoanzeigeDTO();
        try {
            // use prepared stmt
            PreparedStatement preparedStatement = JDBCConnection.getInstance().getPreparedStatement(sql);
            rs = preparedStatement.executeQuery();
            assert (rs != null);
            fillAutoanzeige(rs, autoanzeige, 8);
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.INFO, null, rs);
        } catch (SQLException | DatabaseException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            org.se2.ai.model.dao.AbstractDAO.closeResultset(rs);
        }
        return autoanzeige;
    }

    public boolean updateStatusAutoanzeige(AutoanzeigeDTO s) {
        String sqlArbeitgeber = "UPDATE mmuel72s.autoanzeige " +
                "SET status = ? " +
                "WHERE autoanzeige_id = ?";
        try (PreparedStatement stmt = this.getPreparedStatement(sqlArbeitgeber)) {
            stmt.setString(1, s.getStatus());
            stmt.setInt(2, s.getAutoanzeigenID());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(AutoanzeigeDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }

    public boolean deleteAutoanzeige(AutoanzeigeDTO s) {
        String sqlArbeitgeber = "DELETE FROM mmuel72s.autoanzeige WHERE autoanzeige = ?;" +
               "DELETE FROM mmuel72s.bewerbung WHERE autoanzeige_id = ?;" +
              "DELETE FROM mmuel72s.autoanzeige " +
               "WHERE autoanzeige = ?;"
        ;
        try (PreparedStatement stmt = this.getPreparedStatement(sqlArbeitgeber)) {
            stmt.setInt(1, s.getAutoanzeigenID());
            stmt.setInt(2, s.getAutoanzeigenID());
            stmt.setInt(3, s.getAutoanzeigenID());

            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(AutoanzeigeDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }


}

