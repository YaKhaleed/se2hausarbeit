package org.se2.gui.components;

import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Panel;

import org.se2.ai.control.RegistrationControl;
import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.model.entities.Benutzer;
import org.se2.gui.ui.MyUI;
import org.se2.gui.windows.BestaetigenReg;
import org.se2.services.db.JDBCConnection;
import org.se2.services.util.Views;
import static org.se2.services.util.Roles.KUNDE;
import static org.se2.gui.view.Register.FEHLER;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterSeiteMain extends Panel{
    public static final String CLASSNAME = "Registerseite";
    private String register;
    private String password;
    private RadioButtonGroup<String> single = new RadioButtonGroup<>();


    public RegisterSeiteMain(RadioButtonGroup<String> single) {
        this.single = single;
        // validation experiment
        Binder<Benutzer> binder = new Binder<>();

        // end validation experiment

        final TextField userRegister = new TextField();
        userRegister.setPlaceholder("E-Mail Adresse");


        final PasswordField passwordRegister = new PasswordField();
        passwordRegister.setPlaceholder("Passwort");


        binder.forField(userRegister).asRequired("Sie müssen eine E-Mail Adresse eingeben")
                .withValidator(new EmailValidator("Ungültige E-Mail Adresse"))
                .bind(Benutzer::getEmail, Benutzer::setEmail);

        // password too $hort ;)
        binder.forField(passwordRegister).asRequired("Sie müssen ein Passwort eingeben")
                .withValidator(new StringLengthValidator("Passwort muss zwischen 6 und 20 Zeichen lang sein", 6, 20))
                .bind(Benutzer::getPasswort, Benutzer::setPasswort);


//Vertikales Layout + Hinzufügen der Textfelder
        VerticalLayout layout = new VerticalLayout();
        HorizontalLayout holayout = new HorizontalLayout();
        Button anmelden = new Button("Anmelden");
        anmelden.addStyleName(ValoTheme.BUTTON_LINK);
        anmelden.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.LOGIN));
        holayout.addComponent(anmelden);
        holayout.setComponentAlignment(anmelden, Alignment.MIDDLE_LEFT);
        Button registerb = new Button("Registrieren");
        registerb.addStyleName(ValoTheme.BUTTON_LINK);
        registerb.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.REGISTER));
        holayout.addComponent(registerb);
        holayout.setComponentAlignment(registerb, Alignment.MIDDLE_RIGHT);
        String widthPixels = "400px";
        holayout.setWidth(widthPixels);
        holayout.setHeight("50px");
        layout.addComponent(holayout);
        Label e = new Label("Erstellen Sie ihr CarLook Konto: ");
        layout.addComponent(e);
        Label label = new Label("&nbsp;", ContentMode.HTML);
        layout.addComponent(label);
        layout.addComponent(userRegister);
        layout.addComponent(passwordRegister);
        userRegister.setWidth(widthPixels);
        passwordRegister.setWidth(widthPixels);
        layout.addComponent(single);
        layout.setComponentAlignment(single, Alignment.MIDDLE_CENTER);

//Erstellen und Hinzufügen eines Panels + Platzierung in die Mitte

//Button zum Registrieren
        Button buttonReg = new Button("Registrieren");
        buttonReg.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        buttonReg.setPrimaryStyleName(CLASSNAME + "-registrieren");


        layout.addComponent(buttonReg);
        layout.setComponentAlignment(buttonReg, Alignment.MIDDLE_CENTER);
        Label label3 = new Label("", ContentMode.TEXT);
        layout.addComponent(label3);
        layout.setComponentAlignment(label3, Alignment.MIDDLE_CENTER);

        this.setContent(layout);
        this.setSizeUndefined();


        buttonReg.addClickListener(clickEvent -> {
            register = userRegister.getValue();
            password = passwordRegister.getValue();
            String role = single.getValue();

            // instance of control
            RegistrationControl r = new RegistrationControl();

            boolean allChecksOkay = false;
            try {
                allChecksOkay =
                        r.checkUserExists(register);
            } catch (DatabaseException ex) {
                Notification.show("Fehler", "Registrierung konnte nicht abgeschlossen werden", Notification.Type.ERROR_MESSAGE);

                Logger.getLogger(RegisterSeiteMain.class.getName()).log(Level.SEVERE, null, "Failed on : " + ex);
                // kinda irritating to have to input your email address again
                userRegister.setValue(register);
                passwordRegister.setValue("");
            }
            if (allChecksOkay) {
                allChecksOkay = false;
                try {
                    allChecksOkay =
                            r.registerUser(register, password, role);
                } catch (DatabaseException ex) {
                    Notification.show(FEHLER, "Registrierung konnte nicht abgeschlossen werden" + ex.getReason(), Notification.Type.ERROR_MESSAGE);
                    Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, "Failed on : " + ex);
                    userRegister.setValue(register);
                    passwordRegister.setValue("");

                }

            }

            if (allChecksOkay) {
                Benutzer current = new Benutzer();
                current.setEmail(register);
                current.setPasswort(password);
                current.setRolle(role);
                current.setId(new Integer(VaadinSession.getCurrent().getAttribute("userId").toString()));
                ((MyUI) UI.getCurrent()).setBenutzer(current);
                if (role.equals(KUNDE)) {
                    BestaetigenReg window = new BestaetigenReg("Richten Sie Ihr Konto ein!", Views.REGWEITERS);
                    UI.getCurrent().addWindow(window);
                } else {
                    BestaetigenReg windows = new BestaetigenReg("Richten Sie Ihr Konto ein!", Views.REGWEITERA);
                    UI.getCurrent().addWindow(windows);
                }

            } else {
                Notification.show(FEHLER, "Bitte beachten Sie die Hinweise in den Eingabefelder", Notification.Type.ERROR_MESSAGE);
            }
        });
    }

    public RadioButtonGroup<String> getRadioButton() {
        return single;
    }

    public void setRadioButton(RadioButtonGroup<String> single) {
        this.single = single;
    }
}
