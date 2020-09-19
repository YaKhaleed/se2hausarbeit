package org.se2.ai.model.dao;

import org.se2.ai.control.LoginControl;
import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.control.exceptions.NoSuchUserOrPassword;
import org.se2.ai.model.entities.Benutzer;
import org.se2.services.db.JDBCConnection;
import org.se2.services.util.PasswordAuthentication;
import com.vaadin.server.VaadinSession;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zmorin2s
 */

public class BenutzerDAO extends AbstractDAO {

    private static final String EXCEPTION = "Fehler im SQL-Befehl! Bitte den Programmier benachrichtigen";
    private static BenutzerDAO dao = null;

    private BenutzerDAO() {

    }

    public static BenutzerDAO getInstance() {
        if (dao == null) {
            dao = new BenutzerDAO();
        }
        return dao;
    }

    public static Benutzer getBenutzer(String email) throws DatabaseException {

        ResultSet set = null;

        Benutzer benutzer = null;
        try {
            PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement("SELECT * "
                    + "FROM mmuel72s.benutzer "
                    + "WHERE mmuel72s.benutzer.email = ?");
            statement.setString(1, email);
            set = statement.executeQuery();
            while (set.next()) {
                benutzer = new Benutzer();
                benutzer.setId(set.getInt(1));
                benutzer.setTelefonnummer(set.getString(2));
                benutzer.setAnrede(set.getString(3));
                benutzer.setEmail(set.getString(4));
                benutzer.setPasswort(set.getString(5));
                benutzer.setRolle(set.getString(6));
            }
        } catch (SQLException | DatabaseException e) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, e.getMessage());

            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        } finally {
            org.se2.ai.model.dao.AbstractDAO.closeResultset(set);
            JDBCConnection.getInstance().closeConnection();
        }

        return null;
    }

    //!!!!Brauchen wir die Benutzerrolle?!!!!!

    public static String getBenutzerrolle(String email) {
        ResultSet set = null;
        try {
            PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement("SELECT rolle "
                    + "FROM mmuel72s.benutzer "
                    + "WHERE mmuel72s.benutzer.email = ?");
            statement.setString(1, email);
            set = statement.executeQuery();

            if (set.next()) {
                return set.getString(1);
            }
        } catch (SQLException | DatabaseException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultset(set);
        }

        return null;
    }

    public static Benutzer getBenutzer(String email, String password) throws DatabaseException, NoSuchUserOrPassword {
        ResultSet set;
        final String USER_LOGIN_STATEMENT = "SELECT email, passwort, rolle, anrede, telefonnummer, benutzer_id FROM mmuel72s.benutzer WHERE email = ?";

        try {
            // use prepared statements to mitigate sql injection
            PreparedStatement preparedStatement = JDBCConnection.getInstance().getPreparedStatement(USER_LOGIN_STATEMENT);
            // remember, the int references the index of the item, starting 1
            preparedStatement.setString(1, email);

            // query!
            set = preparedStatement.executeQuery();
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.INFO, null, set);
        } catch (SQLException e) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseException(EXCEPTION);
        }
        String dbPasswordHash = null;
        try {
            if (set.next()) {
                dbPasswordHash = set.getString(2);
            } else {
                //Error Handling
                throw new NoSuchUserOrPassword();
            }
        } catch (SQLException e) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseException(EXCEPTION);
        }
        // user vorhanden. Jetzt Passwort hashes vergleichen
        /**
         *
         * */
        Benutzer benutzer = null;

        PasswordAuthentication authenticator = new PasswordAuthentication();
        // remember, convert to char[] as the string method is deprecated
        char[] c = password.toCharArray();
        // check if hashes match

        try {
            if (authenticator.authenticate(c, dbPasswordHash)) {
                benutzer = new Benutzer();
                benutzer.setEmail(email);
                benutzer.setPasswort(dbPasswordHash);
                benutzer.setRolle(set.getString(3));
                benutzer.setAnrede(set.getString(4));
                benutzer.setTelefonnummer(set.getString(5));
                benutzer.setId(set.getInt(6));
                return benutzer;
            } else {
                throw new NoSuchUserOrPassword();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            AbstractDAO.closeResultset(set);
        }
        return new Benutzer();
    }

    public void deleteUser(String email, String passwort) throws DatabaseException {
        PasswordAuthentication hasher = new PasswordAuthentication();
        char[] c = passwort.toCharArray();
        String passwordHash = hasher.hash(c);
        try (PreparedStatement statement = getPreparedStatement("DELETE FROM mmuel72s.benutzer WHERE email = ? AND passwort = ?;")) {
            statement.setString(1, email);
            statement.setString(2, passwordHash);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, throwables.getMessage(), throwables);
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }

    }

    public boolean checkUserExists(String email) throws DatabaseException {
        ResultSet set = null;

        String sql = "SELECT  COUNT(*) AS rowcount FROM mmuel72s.benutzer " +
                " WHERE mmuel72s.benutzer.email = ?";
        try {

            PreparedStatement preparedStatement = JDBCConnection.getInstance().getPreparedStatement(sql);

            preparedStatement.setString(1, email);

            set = preparedStatement.executeQuery();
            set.next();
            int count = set.getInt("rowcount");
            set.close();
            return count == 0;
        } catch (SQLException e) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new DatabaseException(EXCEPTION);

        } finally {
            closeResultset(set);
            JDBCConnection.getInstance().closeConnection();
        }

    }

    public boolean createBenutzer(String email, String passwort, String rolle) {


        String sql = "INSERT INTO mmuel72s.benutzer(email, passwort, rolle) VALUES (?,?,?)";
        PreparedStatement statement = this.getPreparedStatement(sql);
        PasswordAuthentication hasher = new PasswordAuthentication();

        // convert to char[] as the string method is deprecated
        char[] c = passwort.toCharArray();
        String passwordHash = hasher.hash(c); // password hash
        ResultSet genKeys = null;

        try {
            statement.setString(1, email);
            statement.setString(2, passwordHash);
            statement.setString(3, rolle);
            int rowsChanged = statement.executeUpdate();
            if (rowsChanged == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            genKeys = statement.getGeneratedKeys();
            if (genKeys.next()) {
                Long userId = genKeys.getLong(1);
                VaadinSession.getCurrent().setAttribute("userId", userId);
                logEntry("BenutzerDAO", Level.INFO, "Found userid: " + userId);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

            return true;
        } catch (SQLException ex) {
            logEntry(this.getClass().getName(), Level.SEVERE, ex.getMessage());
            return false;
        } finally {
            closeResultset(genKeys);
        }
    }

    public boolean updateStammdaten(Benutzer u, String anrede, Benutzer user) {
        String updateBenutzerTbl = "UPDATE mmuel72s.benutzer " +
                "SET " +
                "anrede = ?, " +
                "telefonnummer =  ? " +
                "WHERE " +
                "benutzer_id = ?;";
        PreparedStatement statement = this.getPreparedStatement(updateBenutzerTbl);

        try {
            statement.setString(1, anrede);
            statement.setString(2, u.getTelefonnummer());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(VertrieblerDAO.class.getName()).log(Level.SEVERE, ex.getMessage());
            return false;

        }
    }

    //Brauchen wir das?

    public boolean changePassword(String email, String altpasswort, String neupasswort) throws DatabaseException {
        String sql = "UPDATE mmuel72s.benutzer SET passwort = ? WHERE mmuel72s.benutzer.email = ? AND mmuel72s.benutzer.passwort = ?;";


        PasswordAuthentication hasher = new PasswordAuthentication();
        char[] alt = altpasswort.toCharArray();
        char[] neu = neupasswort.toCharArray();


        try (PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql)) {
            String currentHash = getBenutzer(email, altpasswort).getPasswort();
            if (!hasher.authenticate(alt, currentHash)) {
                throw new NoSuchUserOrPassword();
            }
            statement.setString(1, hasher.hash(neu));
            statement.setString(2, email);
            statement.setString(3, currentHash);
            statement.executeUpdate();
            return true;

        } catch (SQLException throwables) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, throwables.getMessage());
            return false;
        } catch (NoSuchUserOrPassword noSuchUserOrPassword) {
            Logger.getLogger(BenutzerDAO.class.getName()).log(Level.SEVERE, noSuchUserOrPassword.getMessage(), noSuchUserOrPassword);
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }
        return false;
    }


}
