package org.se2.gui.components;

import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import org.se2.ai.model.entities.Benutzer;

public class registerseiteMain {
    // FEHLT NOCH VIEL !!! unten
    public static final String CLASSNAME = "Registerseite";
    private String register;
    private String password;
    private RadioButtonGroup<String> xxx = new RadioButtonGroup<>();


    public registerseiteMain(RadioButtonGroup<String> xxx) {
        this.xxx = xxx;
        // validation experiment
        Binder<Benutzer> binder = new Binder<>();

        // end validation experiment

        final TextField userRegister = new TextField();
        userRegister.setPlaceholder("E-Mail Adresse");


        final PasswordField passwordRegister = new PasswordField();
        passwordRegister.setPlaceholder("Passwort");


        binder.forField(userRegister).asRequired("Sie müssen eine Email Adresse eingeben")
                .withValidator(new EmailValidator("Ungültige Email Adresse"))
                .bind(Benutzer::getEmail, Benutzer::setEmail);

        // password too $hort ;)
        binder.forField(passwordRegister).asRequired("Sie müssen ein Passwort eingeben")
                .withValidator(new StringLengthValidator("Passwort muss zwischen 6 und 20 Zeichen lang sein", 6, 20))
                .bind(Benutzer::getPasswort, Benutzer::setPasswort);

    }
}
