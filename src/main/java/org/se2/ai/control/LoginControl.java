package org.se2.ai.control;

import com.vaadin.ui.UI;
import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.control.exceptions.NoSuchUserOrPassword;
import org.se2.ai.model.entities.User;
import org.se2.gui.ui.MyUI;

public class LoginControl {

    private LoginControl(){

    }

    public static void authentification (String email, String password) throws NoSuchUserOrPassword, DatabaseException {

        User user = UserDAO.getUser (email, password);

        ((MyUI) UI.getCurrent()).setUser(user);

        UI.getCurrent().getSession().setAttribute(Roles.CURRENTUSER, user);

        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);


    }

}
