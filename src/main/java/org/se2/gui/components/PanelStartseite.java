package org.se2.gui.components;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.se2.ai.control.LoginControl;
import org.se2.ai.model.entities.Benutzer;
import org.se2.services.util.Roles;
import org.se2.services.util.Views;

import java.io.File;

public class PanelStartseite extends HorizontalLayout {
    public static final String CLASSNAME = "PanelSTART";
    static final String TOPPANELBUTTON = "panelbutton";


    public PanelStartseite() {

        this.setSizeFull();
        Benutzer user = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath +
                "/Image/stealthyalda.png"));
        Image logo = new Image("", resource);
        GridLayout gridTop = new GridLayout(8, 1);
        gridTop.setSizeFull();
        logo.setPrimaryStyleName(CLASSNAME + "-logo");


        gridTop.addComponent(logo, 0, 0);
        Button buttonFuerKunden = new Button("Für Kunden");
        buttonFuerKunden.addStyleName(TOPPANELBUTTON);

        buttonFuerKunden.addStyleName(ValoTheme.BUTTON_LINK);

        buttonFuerKunden.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.REGISTERFUERKUNDE));
        gridTop.addComponent(buttonFuerKunden, 5, 0);

        Button buttonFuerVertriebler = new Button("Für Vertriebler");
        buttonFuerVertriebler.addStyleName(TOPPANELBUTTON);
        buttonFuerVertriebler.addStyleName(ValoTheme.BUTTON_LINK);
        buttonFuerVertriebler.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.REGISTERFUERVERTRIEBLER));
        gridTop.addComponent(buttonFuerVertriebler, 6, 0);


        Button buttonAnmelden = new Button("Anmelden");
        buttonAnmelden.addStyleName(ValoTheme.BUTTON_LINK);
        buttonAnmelden.addStyleName(TOPPANELBUTTON);

        buttonAnmelden.addClickListener(event -> {
            if (user != null) {
                LoginControl.logoutbenutzer();
            } else {
                UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
            }
        });
        gridTop.addComponent(buttonAnmelden, 7, 0);
        gridTop.setComponentAlignment(buttonFuerKunden, Alignment.MIDDLE_RIGHT);
        gridTop.setComponentAlignment(buttonFuerVertriebler, Alignment.MIDDLE_RIGHT);
        gridTop.setComponentAlignment(buttonAnmelden, Alignment.MIDDLE_RIGHT);


        this.addComponent(gridTop);


    }

}
