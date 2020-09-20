package se2hausarbeit.ai.controll;

/**
 * @author qthi2s
 */


import org.junit.Test;
import org.mockito.Mockito;
import org.se2.ai.control.RegistrationControl;
import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.control.exceptions.NoSuchUserOrPassword;
import org.se2.ai.control.exceptions.UserExistsException;
import org.se2.ai.model.dao.BenutzerDAO;
import org.se2.ai.model.entities.Benutzer;
import org.se2.ai.model.entities.Kunde;
import org.se2.gui.view.RegisterSeite;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class RegisterControllTest {

    @Test
    public void checkUserExists() throws UserExistsException, DatabaseException, NoSuchUserOrPassword {

        RegistrationControl rc = mock(RegistrationControl.class);
        assertFalse(rc.checkUserExists("ingo"));

    }

    @Test
    public void registerUser() throws DatabaseException, NoSuchUserOrPassword {

        BenutzerDAO myDAO = BenutzerDAO.getInstance();
        myDAO.createBenutzer("abc@test.de", "one12345", "Kunde");
        //muss nicht bei "ben" als email das angegeben werden, was in Zeile 33 eingetippt wurde?
        Benutzer ben = BenutzerDAO.getBenutzer("one", "one");
        System.out.println(ben.getEmail());

    }

    @Test
    public void registerVertriebler() {


    }

    @Test
    public void registerKunde() {
    }

    @Test
    public void testRegistration() throws Exception {
        Kunde kunde = Mockito.mock(Kunde.class);
        Mockito.when(kunde.getVorname()).thenReturn("Jan");
        Mockito.when(kunde.getNachname()).thenReturn("Müller");
        Mockito.when(kunde.getEmail()).thenReturn("jan@mueller.de");
        Mockito.when(kunde.getPasswort()).thenReturn("12345678");


        assertTrue(kunde.getVorname() == "Jan");
        assertTrue(kunde.getNachname() == "Müller");
        assertTrue(kunde.getEmail() == "jan@mueller.de");
        assertTrue(kunde.getPasswort() == "12345678");
        assertEquals(kunde, kunde);
        RegisterSeite test1 = Mockito.mock(RegisterSeite.class);

    }


}
