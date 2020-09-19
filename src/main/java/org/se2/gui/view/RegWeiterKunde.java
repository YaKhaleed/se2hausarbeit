package org.se2.gui.view;

import org.se2.ai.control.RegistrationControl;
import org.se2.ai.model.DTO.Adresse;
import org.se2.ai.model.DTO.KundeDTO;
import org.se2.ai.model.entities.Benutzer;
import org.se2.gui.components.AnredeField;
import org.se2.gui.components.EmailPaswortField;
import org.se2.gui.components.PanelStartseite;
import org.se2.gui.components.TextFieldForRegWeiter;
import org.se2.gui.ui.MyUI;
import org.se2.gui.windows.BestaetigenReg;
import org.se2.services.util.Roles;
import org.se2.services.util.Views;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zmorin2s
 */
public class RegWeiterKunde extends Register {
    private static final String WIDTH = "500px";
    private static final String WIDTH_250_PX = "250px";
    private static final String WIDTH_230_PX = "230px";
    private static final String WIDTH_515_PX = "515px";
    transient Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();

    public void setUp() {
        PanelStartseite panel = new PanelStartseite();
        VerticalLayout haupt = new VerticalLayout();
        haupt.setWidth(WIDTH);
        panel.setHeight("50px");
        this.addComponent(panel);
        setMargin(true);
        Label label = new Label("<b> Jetzt können Sie nach Autos suchen und reservieren! </b>", ContentMode.HTML);
        label.addStyleName("mytitle");
        label.addStyleName(ValoTheme.LABEL_H1);
        haupt.addComponent(label);
        haupt.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
        //final ComboBox<String> userAnrede = new AnredeField();
        //haupt.addComponent(userAnrede);

        HorizontalLayout hl1 = new HorizontalLayout();
        hl1.setWidth(WIDTH_515_PX);

        final TextFieldForRegWeiter vorname = new TextFieldForRegWeiter("Vorname", WIDTH_250_PX);
        hl1.addComponent(vorname);
        hl1.setComponentAlignment(vorname, Alignment.MIDDLE_LEFT);

        final TextFieldForRegWeiter nachname = new TextFieldForRegWeiter("Nachname", WIDTH_230_PX);
        hl1.addComponent(nachname);
        hl1.setComponentAlignment(nachname, Alignment.MIDDLE_RIGHT);

        haupt.addComponent(hl1);

        EmailPaswortField hl2 = new EmailPaswortField(user);

        haupt.addComponent(hl2);

        /// start edits
        /*
        HorizontalLayout streetNr = new HorizontalLayout();
        streetNr.setSpacing(true);
        streetNr.setWidth(WIDTH_515_PX);


        final TextFieldForRegWeiter strasse = new TextFieldForRegWeiter("Straße", WIDTH_250_PX);
        streetNr.addComponent(strasse);


        final TextFieldForRegWeiter nummer = new TextFieldForRegWeiter("Hausnr.", WIDTH_230_PX);
        streetNr.addComponent(nummer);
        streetNr.setComponentAlignment(nummer, Alignment.MIDDLE_RIGHT);

        final HorizontalLayout plzort = new HorizontalLayout();
        plzort.setWidth(WIDTH_515_PX);

        final TextFieldForRegWeiter plz = new TextFieldForRegWeiter("PLZ", WIDTH_250_PX);
        plzort.addComponent(plz);

        haupt.addComponent(streetNr);

        final TextFieldForRegWeiter ort = new TextFieldForRegWeiter("Ort", WIDTH_230_PX);
        plzort.addComponent(ort);
        plzort.setComponentAlignment(ort, Alignment.MIDDLE_RIGHT);

        haupt.addComponent(plzort);

        final TextFieldForRegWeiter telefon = new TextFieldForRegWeiter("Telefon", WIDTH);
        haupt.addComponent(telefon);
        */


        final Label label2 = new Label("&nbsp;", ContentMode.HTML);
        this.addComponent(label2);

        final Button ubermitteln = new Button();
        ubermitteln.setCaption("Übermitteln");
        ubermitteln.setWidth(WIDTH);

        /*
        binder.forField(strasse).asRequired("Sie müssen eine Gültige Strassenname eingeben")
                .withValidator(new StringLengthValidator("Eingebene Straße nicht gültig", 3, 30))
                .bind(Benutzer::getRolle, Benutzer::setRole);

        binder.forField(plz).asRequired("Sie müssen eine Gültige PLZ eingeben")
                .withValidator(new StringLengthValidator("Eingebene PLZ nicht gültig", 4, 5))
                .withValidator(new RegexpValidator("PLZ darf nur Zahlen enthalten", "^[0-9]*$"))
                .bind(Benutzer::getRole, Benutzer::setRole);

        binder.forField(nummer).asRequired("Sie müssen eine Gültige Hausnummer eingeben")
                .withValidator(new StringLengthValidator("Eingebene Hausnummer nicht gültig", 1, 4))
                .bind(Benutzer::getRole, Benutzer::setRole);

        binder.forField(ort).asRequired("Sie müssen ein Ort eingeben")
                .withValidator(new StringLengthValidator("Ort ist nicht gültig", 3, 30))
                .bind(Benutzer::getRole, Benutzer::setRole);
                */
        binder.forField(vorname).asRequired("Sie müssen Ihre Vorname(n) eingeben")
                .withValidator(new StringLengthValidator("Vorname(n) zu kurz", 3, 30))
                .bind(Benutzer::getRolle, Benutzer::setRolle);
        binder.forField(nachname).asRequired("Sie müssen Ihre Nachname(n) eingeben")
                .withValidator(new StringLengthValidator("Nachname(n) zu kurz", 3, 30))
                .bind(Benutzer::getRolle, Benutzer::setRolle);

        /*
        binder.forField(telefon).asRequired("Sie müssen eine Telefonnummer eingeben")
                .withValidator(new RegexpValidator("Telefonnummer darf nur Zahlen enthalten", "^[0-9]*$"))
                .bind(Benutzer::getRole, Benutzer::setRole);
                */

        ubermitteln.addClickListener(event -> {
            binder.validate();
            if (binder.validate().isOk()) {


                //String anrede = userAnrede.getValue();
                String uservorname = vorname.getValue();
                String username = nachname.getValue();
                //String userstrasse = strasse.getValue();
                //String hausnummer = nummer.getValue();
                //String userort = ort.getValue();
                //int userplz = Integer.parseInt(plz.getValue());
                //String usertelefon = telefon.getValue();
                // instance of control
                RegistrationControl r = new RegistrationControl();
                KundeDTO kunde = new KundeDTO();
                try {
                    //kunde.setAnrede(anrede);
                    kunde.setVorname(uservorname);
                    kunde.setNachname(username);
                    //kunde.setTelefonnummer(usertelefon);
                    //kunde.setAdresse(new Adresse(userstrasse, userplz, hausnummer, userort));
                    r.registerKunde(kunde);
                } catch (Exception e) {
                    Logger.getLogger(RegWeiterKunde.class.getName()).log(Level.SEVERE, e.getMessage(), e);
                }
                user = null;
                BestaetigenReg window = new BestaetigenReg("Registrierung abgeschlossen!", Views.STARTSEITE);
                ((MyUI) UI.getCurrent()).setBenutzer(user);
                VaadinSession.getCurrent().setAttribute(Roles.CURRENTUSER, null);
                UI.getCurrent().addWindow(window);
            } else {
                Notification.show(FEHLER, "Beachten Sie die Hinweise neben den Feldern!", Notification.Type.ERROR_MESSAGE);
            }

        });


        haupt.addComponent(ubermitteln);
        haupt.setComponentAlignment(ubermitteln, Alignment.MIDDLE_CENTER);
        this.addComponent(haupt);
        this.setComponentAlignment(haupt, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setUp();
    }
}
