package se2hausarbeit.ai.controll;
/**
 * @author qthi2s
 */


import org.junit.Test;
import org.se2.ai.control.RegistrationControl;
import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.control.exceptions.UserExistsException;
import org.se2.ai.model.dao.BenutzerDAO;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class KontoControllTest {

    @Test

    public void loescheKonto() throws DatabaseException, UserExistsException {

        RegistrationControl rc = mock(RegistrationControl.class);
        String email = "deletekonto@test.de";
        String pw = "test123";
        rc.registerUser(email, pw, "kunde");
        assertTrue(rc.checkUserExists(email));

        BenutzerDAO benDAO = BenutzerDAO.getInstance();
        benDAO.deleteUser(email, pw);

        assertFalse(rc.checkUserExists(email));

    }



    //@Test
    public void aenderkonto() {

    }
}
