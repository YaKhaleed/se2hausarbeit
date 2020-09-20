package org.se2.gui.components;

import org.se2.ai.control.LoginControl;
import org.se2.ai.model.entities.Benutzer;
import org.se2.gui.ui.MyUI;
import org.se2.services.util.Roles;
import org.se2.services.util.Views;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;


public class TopPanel extends HorizontalLayout {
    static final String TOPPANELBUTTON = "toppanelbutton";


    public TopPanel(Benutzer user) {

        this.setSizeFull();

        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        //FileResource resource = new FileResource(new File();
        //Image logo = new Image("", resource);

        GridLayout gridTop = new GridLayout(8, 1);
        gridTop.setSizeFull();

       // gridTop.addComponent(logo, 0, 0);
        Button buttonFuerKunde = new Button("Für Kunden");

        buttonFuerKunde.addStyleName(ValoTheme.BUTTON_LINK);
        buttonFuerKunde.addStyleName(TOPPANELBUTTON);
        buttonFuerKunde.addClickListener(clickEvent -> {
            if (user.getRolle().equals("Kunde") || user.getRolle().equals("admin")) {
                ((MyUI) UI.getCurrent()).setBenutzer(user);
                UI.getCurrent().getSession().setAttribute(Roles.CURRENTUSER, user);
                UI.getCurrent().getNavigator().navigateTo(Views.DASHBOARDS);
            } else {
                Notification.show("Fehler", "Seite ist nur für Kunden verfügbar", Notification.Type.ERROR_MESSAGE);
            }
        });
        gridTop.addComponent(buttonFuerKunde, 5, 0);

        Button buttonFuerVertriebler = new Button("Für Vertriebler");
        buttonFuerVertriebler.addStyleName(ValoTheme.BUTTON_LINK);
        buttonFuerVertriebler.addStyleName(TOPPANELBUTTON);
        buttonFuerVertriebler.addClickListener(clickEvent -> {
            if (user.getRolle().equals("Vertriebler") || user.getRolle().equals("admin")) {
                ((MyUI) UI.getCurrent()).setBenutzer(user);
                UI.getCurrent().getSession().setAttribute(Roles.CURRENTUSER, user);
                UI.getCurrent().getNavigator().navigateTo(Views.DASHBOARDA);
            } else {
                Notification.show("Fehler", "Seite ist nur für Vertriebler verfügbar", Notification.Type.ERROR_MESSAGE);

            }
        });
        gridTop.addComponent(buttonFuerVertriebler, 6, 0);


        Button buttonAbmelden = new Button("Abmelden");
        buttonAbmelden.addStyleName(ValoTheme.BUTTON_LINK);
        buttonAbmelden.addStyleName(TOPPANELBUTTON);
        buttonAbmelden.addClickListener(event -> LoginControl.logoutbenutzer());
        gridTop.addComponent(buttonAbmelden, 7, 0);
        gridTop.setComponentAlignment(buttonFuerKunde, Alignment.MIDDLE_RIGHT);
        gridTop.setComponentAlignment(buttonFuerVertriebler, Alignment.MIDDLE_RIGHT);
        gridTop.setComponentAlignment(buttonAbmelden, Alignment.MIDDLE_RIGHT);

        this.addComponent(gridTop);


    }

}

