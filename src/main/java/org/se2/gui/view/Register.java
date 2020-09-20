package org.se2.gui.view;

import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.ui.VerticalLayout;
import org.se2.ai.model.entities.Benutzer;

/**
 * @author qthi2s
 */

public class Register extends VerticalLayout implements View {
    public static final String FEHLER = "Fehler";
    static final String Kunde = "Kunde";
    static final String Vertriebler = "Vertriebler";
    static final String HORIZONTAL_WIDTH = "500px";
    final Binder<Benutzer> binder = new Binder<>();

}
