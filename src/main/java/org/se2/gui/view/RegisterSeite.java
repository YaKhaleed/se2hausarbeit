package org.se2.gui.view;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.themes.ValoTheme;
import org.se2.gui.components.PanelStartseite;
import org.se2.gui.components.RegisterSeiteMain;

/**
 * @author qthi2s
 */

public class RegisterSeite extends Register {
    public static final String CLASSNAME = "Registerseite";

    public void setUp() {

        this.addComponent(new PanelStartseite());
        RadioButtonGroup<String> single = new RadioButtonGroup<>();
        single.setItems(Vertriebler, Kunde);
        single.setValue(Kunde);
        single.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
        Panel panel = new RegisterSeiteMain(single);
        setMargin(true);
        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        this.setUp();

    }
}
