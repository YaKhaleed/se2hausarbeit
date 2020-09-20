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

/**
 * @author zmorin2s
 */

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


    public List<String> getMarkeInTitel() {

        String sql = "SELECT titel FROM mmuel72s.autoanzeige ";

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

