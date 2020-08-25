package org.se2.gui.view;

import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.ui.VerticalLayout;
import org.se2.ai.model.entities.Benutzer;

public class register extends VerticalLayout implements View {
    public static final String FEHLER = "Fehler";
    static final String Kunde = "Kunde";
    static final String CarlookMA = "CarlookMA";
    static final String HORIZONTAL_WIDTH = "500px";
    final Binder<Benutzer> binder = new Binder<>();

}
