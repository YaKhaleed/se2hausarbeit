package org.se2.gui.view;

import org.se2.ai.control.RegistrationControl;
import org.se2.ai.model.DTO.Adresse;
import org.se2.ai.model.DTO.VertrieblerDTO;
import org.se2.ai.model.entities.Benutzer;
import org.se2.gui.components.*;
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
public class RegWeiterVertriebler extends Register{
    private static final String WIDTH = "500px";
    private static final String WIDTHB1 = "250px";
    private static final String WIDTHB2 = "230px";


    transient Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();

    public void setUp() {

        VerticalLayout main = new VerticalLayout();
        main.setWidth(WIDTH);

        PanelStartseite panel = new PanelStartseite();
        panel.setHeight("50px");
        this.addComponent(panel);
        setMargin(true);
        Label titel = new Label("<b> Richten Sie Ihr Konto ein! </b>", ContentMode.HTML);
        titel.addStyleName("mytitle");
        titel.addStyleName(ValoTheme.LABEL_H1);
        main.addComponent(titel);
        //final AnredeField userAnrede = new AnredeField();
        //main.addComponent(userAnrede);

        final TextFieldForRegWeiter name = new TextFieldForRegWeiter("Unternehmensname mit Rechtsform", WIDTH);
        main.addComponent(name);

        HorizontalLayout hl2 = new EmailPaswortField(user);

        main.addComponent(hl2);
        /*
        HorizontalLayout streetNr = new HorizontalLayout();
        streetNr.setSpacing(true);
        streetNr.setWidth("515px");


        final TextFieldForRegWeiter strasse = new TextFieldForRegWeiter("StraÃŸe", WIDTHB1);
        streetNr.addComponent(strasse);


        final TextFieldForRegWeiter nummer = new TextFieldForRegWeiter("Hausnr.", WIDTHB2);
        streetNr.addComponent(nummer);
        streetNr.setComponentAlignment(nummer, Alignment.MIDDLE_RIGHT);

        final HorizontalLayout plzort = new HorizontalLayout();
        plzort.setWidth("515px");

        final TextFieldForRegWeiter plz = new TextFieldForRegWeiter("PLZ", WIDTHB1);
        plzort.addComponent(plz);

        main.addComponent(streetNr);

        final TextFieldForRegWeiter ort = new TextFieldForRegWeiter("Ort", WIDTHB2);
        plzort.addComponent(ort);
        plzort.setComponentAlignment(ort, Alignment.MIDDLE_RIGHT);

        main.addComponent(plzort);

        final TextFieldForRegWeiter telefon = new TextFieldForRegWeiter("Telefon", WIDTH);
        main.addComponent(telefon);


         */
        final Label label2 = new Label("&nbsp;", ContentMode.HTML);
        main.addComponent(label2);

        final Button ubermitteln = new ButtonComponent("Ãœbermitteln", WIDTH);

        /*
        binder.forField(strasse).asRequired("Sie mÃ¼ssen eine GÃ¼ltige Strassenname eingeben")
                .withValidator(new StringLengthValidator("Eingebene StraÃŸe nicht gÃ¼ltig", 3, 30))
                .bind(Benutzer::getRole, Benutzer::setRole);

        binder.forField(plz).asRequired("Sie mÃ¼ssen eine GÃ¼ltige PLZ eingeben")
                .withValidator(new StringLengthValidator("Eingebene PLZ nicht gÃ¼ltig", 4, 5))
                .withValidator(new RegexpValidator("PLZ darf nur Zahlen enthalten", "^[0-9]*$"))
                .bind(Benutzer::getRole, Benutzer::setRole);

        binder.forField(nummer).asRequired("Sie mÃ¼ssen eine GÃ¼ltige Hausnummer eingeben")
                .withValidator(new StringLengthValidator("Eingebene Hausnummer nicht gÃ¼ltig", 1, 4))
                .bind(Benutzer::getRole, Benutzer::setRole);

        binder.forField(ort).asRequired("Sie mÃ¼ssen ein Ort eingeben")
                .withValidator(new StringLengthValidator("Ort ist nicht gÃ¼ltig", 3, 30))
                .bind(Benutzer::getRole, Benutzer::setRole);

         */
        binder.forField(name).asRequired("Sie mÃ¼ssen eine Name eingeben")
                .withValidator(new StringLengthValidator("Vorname(n) zu kurz", 3, 30))
                .bind(Benutzer::getRolle, Benutzer::setRolle);

        /*
        binder.forField(telefon).asRequired("Sie mÃ¼ssen eine Telefonnummer eingeben")
                .withValidator(new RegexpValidator("Telefonnummer darf nur Zahlen enthalten", "^[0-9]*$"))
                .bind(Benutzer::getRole, Benutzer::setRole);

         */

        ubermitteln.addClickListener(event -> {
            binder.validate();

            if (binder.validate().isOk()) {
                //String anrede = userAnrede.getValue().toString();
                String nameVertriebler = name.getValue();
                //String userstrasse = strasse.getValue();
                //String hausnummer = nummer.getValue();
                //String userort = ort.getValue();
                //int userplz = Integer.parseInt(plz.getValue());
                //String usertelefon = telefon.getValue();

                // instance of control
                RegistrationControl r = new RegistrationControl();
                VertrieblerDTO ag = new VertrieblerDTO();
                try {
                    //ag.setAdresse(new Adresse(userstrasse, userplz, hausnummer, userort));
                    //ag.setTelefonnummer(usertelefon);
                    ag.setName(nameVertriebler);

                    //r.registerVertriebler(ag, anrede);

                    user = null;
                    BestaetigenReg window = new BestaetigenReg("Registrierung abgeschlossen!", Views.STARTSEITE);
                    UI.getCurrent().addWindow(window);
                    ((MyUI) UI.getCurrent()).setBenutzer(user);
                    VaadinSession.getCurrent().setAttribute(Roles.CURRENTUSER, null);

                    UI.getCurrent().addWindow(window);
                } catch (Exception ex) {
                    Logger.getLogger(RegWeiterVertriebler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                    Notification.show(FEHLER, "Ihre Daten konnten nicht gespeichert werden: " + ex.getMessage(), Notification.Type.ERROR_MESSAGE);
                    //telefon.setValue(usertelefon);
                    //ort.setValue(userort);
                    //plz.setValue(String.valueOf(userplz));
                    //strasse.setValue(userstrasse);
                }
            } else {
                Notification.show(FEHLER, "Beachten Sie die Hinweise neben den Feldern!", Notification.Type.ERROR_MESSAGE);
            }

        });


        main.addComponent(ubermitteln);
        this.addComponent(main);
        this.setComponentAlignment(main, Alignment.MIDDLE_CENTER);


    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setUp();
    }
}

