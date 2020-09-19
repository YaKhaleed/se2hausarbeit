package org.se2.ai.model.dao;

import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.model.DTO.AutoanzeigeDTO;
import org.se2.ai.model.DTO.ReservierungDTO;
import org.se2.ai.model.entities.Autoanzeige;
import org.se2.ai.model.entities.Kunde;
import org.se2.ai.model.entities.Vertriebler;
import org.se2.services.db.JDBCConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zmorin2s
 */

public class ReservierungDAO extends AbstractDAO{

    private static ReservierungDAO dao = null;

    private ReservierungDAO(){

    }

    public static ReservierungDAO getInstance() {
        if (dao == null) dao = new ReservierungDAO();
        return dao;

    }

    //nochmal überlegen, ob beschreibung nötig ist
    public boolean createReservierung(AutoanzeigeDTO a, ReservierungDTO r, Kunde k) {
        String sql = "INSERT INTO mmuel72s.reservierung(reservierung_id, kunde_id, autoanzeige_id, titel, datum, beschreibung, status) VALUES (default,?,?,?,?,?,?,?)";


        try (PreparedStatement statement = this.getPreparedStatement(sql)) {
            statement.setInt(1, a.getAutoanzeigenID());
            statement.setInt(2, k.getKundenID());
            statement.setInt(3, a.getAutoanzeigenID());
            statement.setString(4, a.getTitel());
            statement.setDate(5, Date.valueOf(r.getDatum()));
            statement.setString(6, a.getBeschreibung());
            statement.setString(7, "reserviert");


            int rowsChanged = statement.executeUpdate();
            setReservierungID(r);
            if (rowsChanged == 0) {
                throw new SQLException("Creating bewerbung failed, no rows affected.");
            }
            return true;
        } catch (SQLException ex) {
            logEntry(this.getClass().getName(), Level.SEVERE, ex.getMessage());
            return false;
        } finally {
            try {
                JDBCConnection.getInstance().closeConnection();
            } catch (DatabaseException e) {
                logEntry(this.getClass().getName(), Level.SEVERE, e.getMessage());
            }
        }
    }

    private void setReservierungID(ReservierungDTO r) throws SQLException {
        Statement statement = this.getStatement();
        ResultSet rs = null;

        int currentValue = 0;
        try {
            rs = statement.executeQuery("SELECT max(mmuel72s.reservierung.reservierung_id) FROM mmuel72s.reservierung");

            assert rs != null;
            rs.next();
            currentValue = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ReservierungDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultset(rs);
        }
        r.setId(currentValue);
    }

    //Eventuell ändern. Nach Kunden sortieren ist nicht ganz schlüssig
    public List<ReservierungDTO> getReservierungFromKunde(Kunde k) {
        //v.name: vertrieblername
        String sql = "SELECT a.titel, v.name, r.status\n" +
                "FROM mmuel72s.reservierung r\n" +
                "JOIN mmuel72s.autoanzeige a ON r.autoanzeige_id = a.autoanzeige_id\n" +
                "JOIN mmuel72s.kunde k ON r.kunde_id = k.kunde_id\n" +
                "JOIN mmuel72s.vertriebler v ON v.vertriebler_id = a.vertriebler_id\n" +
                "WHERE k.student_id = ? " +
                "ORDER BY r.reservierung_id DESC\n";


        ResultSet rs = null;
        List<ReservierungDTO> liste = new ArrayList<>();
        try {
            // use prepared stmt
            PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);
            statement.setInt(1, k.getKundenID());
            rs = statement.executeQuery();
            assert (rs != null);
            while (rs.next()) {
                ReservierungDTO reservierung = new ReservierungDTO();
                Vertriebler v = new Vertriebler();
                AutoanzeigeDTO st = new AutoanzeigeDTO();
                st.setTitel(rs.getString(1));
                //wirklich vertrieblerID?
                v.setVertrieblerID(rs.getInt(2));
                reservierung.setVertriebler(v);
                reservierung.setStatus(rs.getString(3));
                reservierung.setAutoanzeige(st);
                liste.add(reservierung);
            }
        } catch (SQLException | DatabaseException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        } finally {
            closeResultset(rs);
        }
        return liste;

    }

    //nötig?
    /*
    public List<ReservierungDTO> getBewerbungFromArbeitgeber(Arbeitgeber a) {

        String sql = "SELECT s.nachname, s.vorname, a.titel, b.datum, s.student_id, s.benutzer_id, b.anschreiben," +
                "b.erfahrung, b.zeugnisse, b.bewerbung_id\n" +
                "FROM stealthyalda.bewerbung b\n" +
                "JOIN stealthyalda.stellenanzeige a ON b.stellenanzeige_id = a.stellenanzeige_id\n" +
                "JOIN stealthyalda.student s ON b.student_id = s.student_id\n" +
                "JOIN stealthyalda.arbeitgeber u ON u.arbeitgeber_id = a.arbeitgeber_id\n" +
                "WHERE u.arbeitgeber_id = ?\n" +
                "AND b.status = 'gesendet'\n" +
                "ORDER BY b.bewerbung_id";
        ResultSet rs = null;
        List<BewerbungCollAtHBRSDTO> liste = new ArrayList<>();
        try {
            // use prepared stmt
            PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);
            statement.setInt(1, a.getArbeitgeberId());
            rs = statement.executeQuery();
            assert (rs != null);
            while (rs.next()) {
                BewerbungCollAtHBRSDTO bewerbung = new BewerbungCollAtHBRSDTO();
                Student s = new Student();
                StellenanzeigeDTO st = new StellenanzeigeDTO();
                s.setNachname(rs.getString(1));
                s.setVorname(rs.getString(2));
                st.setTitel(rs.getString(3));
                s.setStudentId(rs.getInt(5));
                s.setId(rs.getInt(6));
                bewerbung.setAnschreiben(rs.getString(7));
                bewerbung.setErfahrung(rs.getString(8));
                bewerbung.setZertifikat(rs.getString(9));
                bewerbung.setId(rs.getInt(10));
                bewerbung.setDatum(rs.getDate(4).toLocalDate());
                bewerbung.setStudent(s);
                bewerbung.setStellenanzeige(st);
                liste.add(bewerbung);
            }
        } catch (SQLException | DatabaseException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        } finally {
            closeResultset(rs);
        }
        return liste;

    }
    */

    public boolean updateStatusBewerbung(ReservierungDTO reservierung) {
        String sqlArbeitgeber = "UPDATE mmuel72s.reservierung " +
                "SET status = ? " +
                "WHERE reservierung_id = ?";
        try (PreparedStatement stmt = this.getPreparedStatement(sqlArbeitgeber)) {
            stmt.setString(1, reservierung.getStatus());
            stmt.setInt(2, reservierung.getId());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ReservierungDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }
}
