package org.se2.gui.components;

import org.se2.ai.model.entities.Benutzer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

/**
 * @author zmorin2s
 */

public class EmailPaswortField extends HorizontalLayout {
    public EmailPaswortField(Benutzer user) {
        this.setWidth("500px");
        final TextField email = new TextField();
        email.setWidth("250px");
        email.setValue(user.getEmail());
        email.setReadOnly(true);
        this.addComponent(email);

        final PasswordField passwort = new PasswordField();
        passwort.setValue(user.getPasswort());
        passwort.setWidth("230px");
        passwort.setReadOnly(true);
        this.addComponent(passwort);
        this.setComponentAlignment(passwort, Alignment.MIDDLE_RIGHT);
    }
}

