package org.se2.gui.components;

import org.se2.ai.model.entities.Benutzer;
import org.se2.gui.view.LoggedinSeite;

/**
 * @author zmorin2s
 */

public class ProfilVerwalten extends LoggedinSeite {
    boolean isAdmin;


    public ProfilVerwalten(Benutzer user) {
        isAdmin = user.getRolle().equals("admin");

    }

}
