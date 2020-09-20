package org.se2.gui.view;

import org.se2.ai.model.entities.Benutzer;
import org.se2.services.util.Roles;
import org.se2.services.util.Views;
import com.vaadin.navigator.View;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author qthi2s
 */

public class LoggedinSeite extends VerticalLayout implements View {

    protected transient Benutzer user = (Benutzer) UI.getCurrent().getSession().getAttribute(Roles.CURRENTUSER);

    public boolean isLoggedIn() {
        if (user == null) {
            UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
        }
        return true;
    }
}

