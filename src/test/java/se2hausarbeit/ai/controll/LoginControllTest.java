package se2hausarbeit.ai.controll;

/**
 * @author qthi2s
 */


import org.junit.Test;
import org.se2.ai.control.LoginControl;
import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.control.exceptions.NoSuchUserOrPassword;

public class LoginControllTest {


    @Test(expected = NoSuchUserOrPassword.class)
    public void checkAuthenticationNeg2() throws NoSuchUserOrPassword, DatabaseException {
        //unregistered user should fail
        LoginControl.authentification("nicht null", "nicht null");
    }


    @Test(expected = NullPointerException.class)
    public void logoutUserNeg() {
        LoginControl.logoutbenutzer();
    }


    @Test
    public void checkAuthentification() {

    }

    @Test
    public void logoutUser() {
    }
}
