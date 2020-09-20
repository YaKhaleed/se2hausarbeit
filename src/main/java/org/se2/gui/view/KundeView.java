package org.se2.gui.view;

import org.se2.services.util.Roles;
import org.se2.services.util.Views;
import com.vaadin.ui.UI;

import java.util.Objects;

/**
 * @author zmorin2s
 */

public class KundeView extends LoggedinSeite {

    public KundeView() {
        if (!Objects.equals(user.getRolle(), Roles.KUNDE)) {
            UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        }
    }
}

