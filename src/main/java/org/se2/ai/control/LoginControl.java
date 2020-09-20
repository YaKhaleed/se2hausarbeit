package org.se2.ai.control;

import com.vaadin.ui.UI;
import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.control.exceptions.NoSuchUserOrPassword;
import org.se2.ai.model.dao.BenutzerDAO;
import org.se2.ai.model.entities.Benutzer;
import org.se2.gui.ui.MyUI;
import org.se2.services.util.Roles;
import org.se2.services.util.Views;


public class LoginControl {

    private LoginControl(){

    }


    public static void authentification (String email, String password) throws NoSuchUserOrPassword, DatabaseException {

        Benutzer benutzer = BenutzerDAO.getBenutzer (email, password);

        ((MyUI) UI.getCurrent()).setBenutzer(benutzer);

        UI.getCurrent().getSession().setAttribute(Roles.CURRENTUSER, benutzer);

        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);


    }

    public static void logoutbenutzer() {
        UI.getCurrent().close();
        UI.getCurrent().getSession().close();
        UI.getCurrent().getPage().setLocation(Views.LOGIN);
    }


}
