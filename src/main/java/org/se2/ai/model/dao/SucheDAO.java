package org.se2.ai.model.dao;

import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.model.entities.Vertriebler;
import org.se2.services.db.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SucheDAO extends AbstractDAO {
    private static SucheDAO dao;

    private SucheDAO() {
        System.out.println("Gib bei der Suche nach einem Auto die Automarke Deiner Wahl ein.");
    }

    public static SucheDAO getInstance() {
        if (dao == null) {
            dao = new SucheDAO();
        }
        return dao;
    }

    /*
    private List<String> hilfe2(String sql1, String sql2) {
        ResultSet set = null;
        ResultSet set2 = null;
        List<String> liste = new ArrayList<>();
        try {
            Statement statement = this.getStatement();
            set = statement.executeQuery(sql1);
            while (true) {
                assert set != null;
                if (!set.next()) break;
                liste.add(set.getString(1));
            }
            set2 = statement.executeQuery(sql2);
            while (true) {
                assert set2 != null;
                if (!set2.next()) break;
                liste.add(set2.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SucheDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                JDBCConnection.getInstance().closeConnection();
            } catch (DatabaseException e) {
                Logger.getLogger(SucheDAO.class.getName()).log(Level.SEVERE, null, e);

            }
            org.se2.ai.model.dao.AbstractDAO.closeResultset(set);
            org.se2.ai.model.dao.AbstractDAO.closeResultset(set2);
        }


        return liste;

    }


    public List<String> getJobtitelOrArbeitgeber() {
        String sql1 = "SELECT titel FROM stealthyalda.stellenanzeige ";
        String sql2 = "select distinct a.unternehmen\n" +
                "from stealthyalda.arbeitgeber a\n" +
                "join stealthyalda.stellenanzeige s\n" +
                "on s.arbeitgeber_id = a.arbeitgeber_id";

        return hilfe2(sql1, sql2);

    }*/

    public List<String> getMarkeInTitel(String marke) {

        String sql = "SELECT titel FROM mmuel72s.autoanzeige " +
                "WHERE LOWER(titel) LIKE LOWER('%" + marke + "%');";

        return hilfe(sql);
    }

    private List<String> hilfe(String sql) {
        ResultSet set = null;
        List<String> liste = new ArrayList<>();

        try {
            Statement statement = this.getStatement();
            set = statement.executeQuery(sql);
            while (true) {
                assert set != null;
                if (!set.next()) break;
                liste.add(set.getString(1));
            }
        } catch (SQLException e) {
            Logger.getLogger(SucheDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                JDBCConnection.getInstance().closeConnection();
            } catch (DatabaseException e) {
                Logger.getLogger(SucheDAO.class.getName()).log(Level.SEVERE, null, e);
            }
            org.se2.ai.model.dao.AbstractDAO.closeResultset(set);
        }


        return liste;
    }
/*
    public List<String> getStellenanzeigeFuerArbeitgeber(Arbeitgeber a) {

        String sql = "select distinct s.titel\n" +
                "from stealthyalda.arbeitgeber a\n" +
                "JOIN stealthyalda.stellenanzeige s ON s.arbeitgeber_id = a.arbeitgeber_id\n" +
                "where a.arbeitgeber_id = '" + a.getArbeitgeberId() + "'";

        return hilfe(sql);
    }

    public List<String> getBewerber(Arbeitgeber a) {
        String sql1 = "select distinct s.nachname\n" +
                "from stealthyalda.bewerbung b\n" +
                "JOIN stealthyalda.stellenanzeige a ON b.stellenanzeige_id = a.stellenanzeige_id\n" +
                "JOIN stealthyalda.arbeitgeber u ON u.arbeitgeber_id = a.arbeitgeber_id\n" +
                "join stealthyalda.student s ON b.student_id = s.student_id\n" +
                "where a.arbeitgeber_id = '" + a.getArbeitgeberId() + "'";

        return hilfe(sql1);
    }
*/
}
