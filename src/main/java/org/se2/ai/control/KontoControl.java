package org.se2.ai.control;

import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.model.dao.BenutzerDAO;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KontoControl {

    public void löschekonto(String email, String password) throws DatabaseException {
        BenutzerDAO.getInstance().deleteUser(email, password);

    }

    public boolean änderekonto(String email, String altpassword, String neupassword) {
        boolean check = false;
        if (altpassword.equals(neupassword)) {
            return check; // ;)
        }
        try {
            check = BenutzerDAO.getInstance().changepassword(email, altpassword, neupassword);
        } catch (DatabaseException e) {
            Logger.getLogger(KontoControl.class.getName()).log(Level.SEVERE, e.getReason(), e);
            return false;
        }

        return check;
    }
}
