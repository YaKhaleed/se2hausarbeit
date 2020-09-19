package org.se2.gui.view;

import org.se2.services.util.Roles;
import org.se2.services.util.Views;
import com.vaadin.ui.UI;

import java.util.Objects;

public class KundeView extends LoggedinSeite {
    /**
     * Check if user is a student
     */
    public KundeView() {
        if (!Objects.equals(user.getRolle(), Roles.KUNDE)) {
            UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        }
    }
}

