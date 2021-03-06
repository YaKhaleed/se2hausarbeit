package org.se2.gui.view;

import org.se2.ai.control.LoginControl;
import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.control.exceptions.NoSuchUserOrPassword;
import org.se2.ai.model.entities.Benutzer;
import org.se2.gui.components.PanelStartseite;
import org.se2.gui.ui.MyUI;
import org.se2.services.util.Views;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;

import static org.se2.services.util.Roles.KUNDE;
import static org.se2.services.util.Roles.VERTRIEBLER;

/**
 * @author qthi2s
 */

public class LoginSeite extends VerticalLayout implements View {
    public static final String CLASSNAME = "LOGINSEITE";
    //Geschätztes Leerzeichen "No Break Space"
    private static final String BLANK_SPACE = "&nbsp";


    public void setUp() {

//Gesamtgröße des Bildschirms auf komplette Größe beziehen


//Textfeld Login
        final TextField userLogin = new TextField();
        userLogin.setPlaceholder("E-Mail ");


//Textfelt Passwort
        final PasswordField passwordField = new PasswordField();
        passwordField.setPlaceholder("Passwort ");





        GridLayout mainGrid = new GridLayout(6, 6);
        mainGrid.setSizeFull();

        Panel panel = new Panel();
        panel.addStyleName("login");

//Vertikales Layout + Hinzufügen der Textfelder
        GridLayout layout = new GridLayout(4, 12);

        //BUTTON-ANMELDEN
        Button buttonAn = new Button("Anmelden");
        buttonAn.setPrimaryStyleName(CLASSNAME + "-anmelden");

        //BUTTON-REGISTRIEREN
        Button buttonReg = new Button("Registrieren");
        buttonReg.setPrimaryStyleName(CLASSNAME + "-registrieren");

        buttonAn.addStyleName(ValoTheme.BUTTON_LINK);
        buttonAn.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.LOGIN));
        buttonReg.addStyleName(ValoTheme.BUTTON_LINK);
        buttonReg.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.REGISTER));
        layout.addComponent(buttonAn, 0, 0);
        layout.setComponentAlignment(buttonAn, Alignment.MIDDLE_CENTER);
        layout.addComponent(buttonReg, 3, 0);
        layout.setComponentAlignment(buttonReg, Alignment.MIDDLE_CENTER);

        Label label = new Label("Willkommen zurück!", ContentMode.PREFORMATTED);
        label.setPrimaryStyleName(CLASSNAME + "-willkommen");

        layout.addComponent(label, 0, 1, 3, 1);
        layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        layout.addComponent(userLogin, 0, 3, 3, 3);
        layout.setComponentAlignment(userLogin, Alignment.MIDDLE_LEFT);
        Label label5 = new Label(BLANK_SPACE, ContentMode.HTML);
        layout.addComponent(label5, 0, 4, 3, 4);

        layout.addComponent(passwordField, 0, 5, 3, 5);
        layout.setComponentAlignment(passwordField, Alignment.MIDDLE_LEFT);


        Button buttonPasswortvergessen = new Button("Passwort vergessen?");
        buttonPasswortvergessen.addStyleName(ValoTheme.BUTTON_LINK);
        buttonPasswortvergessen.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.PASSWORTVERGESSEN));
        buttonPasswortvergessen.setPrimaryStyleName(CLASSNAME + "-pwVergessen");


        layout.addComponent(buttonPasswortvergessen, 2, 6, 3, 6);
        layout.setComponentAlignment(buttonPasswortvergessen, Alignment.MIDDLE_RIGHT);

        userLogin.setWidth("500px");
        passwordField.setWidth("500px");

        CheckBox checkbox1 = new CheckBox("Angemeldet bleiben");
        checkbox1.setPrimaryStyleName(CLASSNAME + "-check");

        layout.addComponent(checkbox1, 0, 6, 1, 6);
        layout.setComponentAlignment(checkbox1, Alignment.MIDDLE_LEFT);


//Platzhalter
        Label label2 = new Label(BLANK_SPACE, ContentMode.HTML);
        layout.addComponent(label2, 0, 7, 3, 7);

//Button zum Login + Symbol auf Button
        Button buttonLogin = new Button("Anmelden");
        buttonLogin.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        buttonLogin.setPrimaryStyleName(CLASSNAME + "-login");


        layout.addComponent(buttonLogin, 0, 8, 3, 8);
        layout.setComponentAlignment(buttonLogin, Alignment.MIDDLE_CENTER);

        Label label3 = new Label("", ContentMode.TEXT);

        layout.addComponent(label3, 0, 9, 3, 9);
        layout.setComponentAlignment(label3, Alignment.MIDDLE_CENTER);

        Label label4 = new Label(BLANK_SPACE, ContentMode.HTML);
        layout.addComponent(label4, 0, 11, 3, 11);
        panel.setContent(layout);
        panel.setSizeUndefined();


        buttonLogin.addClickListener(clickEvent -> {
            String login = userLogin.getValue();
            String password = passwordField.getValue();

            try {
                LoginControl.authentification(login, password);
            } catch (NoSuchUserOrPassword ex) {
                Notification.show("Fehler", "Login oder Passwort falsch", Notification.Type.ERROR_MESSAGE);
                userLogin.setValue("");
                passwordField.setValue("");
            } catch (DatabaseException ex) {
                Notification.show("DB-Fehler", ex.getReason(), Notification.Type.ERROR_MESSAGE);
                userLogin.setValue("");
                passwordField.setValue("");
            }
        });


        mainGrid.addComponent(panel, 3, 2, 4, 4);
        this.addComponent(new PanelStartseite());
        this.addComponent(mainGrid);
        this.setComponentAlignment(mainGrid, Alignment.MIDDLE_CENTER);
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();
        if (user != null) {
            if(user.getRolle()==KUNDE) {
                UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
            }
            UI.getCurrent().getNavigator().navigateTo(Views.AUTOANZEIGEERSTELLEN);
        }

                else {
            this.setUp();
        }
    }
}
