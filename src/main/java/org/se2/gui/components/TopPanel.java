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
        FileResource resource = new FileResource(new File(basepath +
                "/Image/stealthyalda1.png"));
        Image logo = new Image("", resource);

        GridLayout gridTop = new GridLayout(8, 1);
        gridTop.setSizeFull();

        gridTop.addComponent(logo, 0, 0);
        Button buttonFuerStudent = new Button("FÃ¼r Kunden");

        buttonFuerStudent.addStyleName(ValoTheme.BUTTON_LINK);
        buttonFuerStudent.addStyleName(TOPPANELBUTTON);
        buttonFuerStudent.addClickListener(clickEvent -> {
            if (user.getRolle().equals("Kunde") || user.getRolle().equals("admin")) {
                ((MyUI) UI.getCurrent()).setBenutzer(user);
                UI.getCurrent().getSession().setAttribute(Roles.CURRENTUSER, user);
                UI.getCurrent().getNavigator().navigateTo(Views.DASHBOARDS);
            } else {
                Notification.show("Fehler", "Seite ist nur fÃ¼r Kunden verfÃ¼gbar", Notification.Type.ERROR_MESSAGE);
            }
        });
        gridTop.addComponent(buttonFuerStudent, 5, 0);

        Button buttonFuerArbeitgeber = new Button("FÃ¼r Arbeitgeber");
        buttonFuerArbeitgeber.addStyleName(ValoTheme.BUTTON_LINK);
        buttonFuerArbeitgeber.addStyleName(TOPPANELBUTTON);
        buttonFuerArbeitgeber.addClickListener(clickEvent -> {
            if (user.getRolle().equals("Arbeitgeber") || user.getRolle().equals("admin")) {
                ((MyUI) UI.getCurrent()).setBenutzer(user);
                UI.getCurrent().getSession().setAttribute(Roles.CURRENTUSER, user);
                UI.getCurrent().getNavigator().navigateTo(Views.DASHBOARDA);
            } else {
                Notification.show("Fehler", "Seite ist nur fÃ¼r Arbeitgeber verfÃ¼gbar", Notification.Type.ERROR_MESSAGE);

            }
        });
        gridTop.addComponent(buttonFuerArbeitgeber, 6, 0);


        Button buttonAbmelden = new Button("Abmelden");
        buttonAbmelden.addStyleName(ValoTheme.BUTTON_LINK);
        buttonAbmelden.addStyleName(TOPPANELBUTTON);
        buttonAbmelden.addClickListener(event -> LoginControl.logoutbenutzer());
        gridTop.addComponent(buttonAbmelden, 7, 0);
        gridTop.setComponentAlignment(buttonFuerStudent, Alignment.MIDDLE_RIGHT);
        gridTop.setComponentAlignment(buttonFuerArbeitgeber, Alignment.MIDDLE_RIGHT);
        gridTop.setComponentAlignment(buttonAbmelden, Alignment.MIDDLE_RIGHT);

        this.addComponent(gridTop);


    }

}

