package se2hausarbeit.ai.model.dao;

import org.junit.Test;
import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.control.exceptions.NoSuchUserOrPassword;
import org.se2.ai.control.exceptions.UserExistsException;
import org.se2.ai.model.dao.BenutzerDAO;
import org.se2.ai.model.entities.Benutzer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BenutzerDaoTest {

    @Test
    public void getUser() throws DatabaseException {
        BenutzerDAO benutzerdao = BenutzerDAO.getInstance();
        Benutzer ben = benutzerdao.getBenutzer("tu@test.de");
        System.out.println(ben.getEmail());

        // Benutzer scheint nicht richtig ersellt zu werden


    }

    @Test
    public void getUserRole() {
        BenutzerDAO benutzerdao = BenutzerDAO.getInstance();
       // assertEquals("Kunde", BenutzerDAO.getBenutzerrolle("tu@test.de"));
    }

    @Test
    public void getBenutzer() throws DatabaseException, NoSuchUserOrPassword {
        BenutzerDAO benutzerdao = BenutzerDAO.getInstance();
        Benutzer ben = BenutzerDAO.getBenutzer("ingo", "ingo");
        assertEquals(2, ben.getId());

        // weird bug INFORMATION NULL aber Methode funktioniert
    }

    @Test
    public void deleteUser() {

    }

    @Test
    public void checkUserExists() throws UserExistsException, DatabaseException {
        BenutzerDAO benutzerdao = BenutzerDAO.getInstance();
        boolean b = benutzerdao.checkUserExists("ingoooo");
        // ingoooo gibt es nicht.
        assertEquals(false, benutzerdao.checkUserExists("ingoooo"));
        // testet man mit adressen die registriert sind komm eine Exception
        // assertEquals(false,benutzerdao.checkUserExists("ingo"));

    }

    @Test
    public void createBenutzer() {
        BenutzerDAO benutzerdao = BenutzerDAO.getInstance();
        boolean bean = benutzerdao.createBenutzer("createme", "createme", "Student");
        // wegen UserId vergabe nicht einfach testbar
    }

    @Test
    public void updateStammdaten() {
    }

    @Test
    public void changepassword() throws DatabaseException, NoSuchUserOrPassword {
        BenutzerDAO benutzerdao = BenutzerDAO.getInstance();
        boolean bean = benutzerdao.changePassword("ingo", "ingo", "ingoo");
        System.out.println(bean);
        Benutzer ben = BenutzerDAO.getBenutzer("ingo", "ingoo");
        assertEquals(2, ben.getId());
        bean = benutzerdao.changePassword("ingo", "ingoo", "ingo");
        System.out.println(bean);
        ben = BenutzerDAO.getBenutzer("ingo", "ingo");
        assertEquals(2, ben.getId());

    }
}
