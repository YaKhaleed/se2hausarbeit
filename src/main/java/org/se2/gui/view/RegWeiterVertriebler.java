package org.se2.gui.view;

import org.se2.ai.control.RegistrationControl;
import org.se2.ai.model.DTO.VertrieblerDTO;
import org.se2.ai.model.entities.Benutzer;
import org.se2.gui.components.*;
import org.se2.gui.ui.MyUI;
import org.se2.gui.windows.BestaetigenReg;
import org.se2.services.util.Roles;
import org.se2.services.util.Views;
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
        final AnredeField userAnrede = new AnredeField();
        main.addComponent(userAnrede);

        final TextFieldForRegWeiter name = new TextFieldForRegWeiter("Ihren Nachnamen eingeben", WIDTH);
        main.addComponent(name);

        HorizontalLayout hl2 = new EmailPaswortField(user);

        main.addComponent(hl2);

        final Label label2 = new Label("&nbsp;", ContentMode.HTML);
        main.addComponent(label2);

        final Button ubermitteln = new ButtonComponent("Übermitteln", WIDTH);

        binder.forField(name).asRequired("Sie müssen einen Name eingeben")
                .withValidator(new StringLengthValidator("Vorname(n) zu kurz", 3, 30))
                .bind(Benutzer::getRolle, Benutzer::setRolle);

        ubermitteln.addClickListener(event -> {
            binder.validate();

            if (binder.validate().isOk()) {
                String anrede = userAnrede.getValue().toString();
                String nameVertriebler = name.getValue();

                // instance of control
                RegistrationControl r = new RegistrationControl();
                VertrieblerDTO ag = new VertrieblerDTO();
                try {
                    ag.setName(nameVertriebler);

                    r.registerVertriebler(ag, anrede);

                    user = null;
                    BestaetigenReg window = new BestaetigenReg("Registrierung abgeschlossen!", Views.STARTSEITE);
                    UI.getCurrent().addWindow(window);
                    ((MyUI) UI.getCurrent()).setBenutzer(user);
                    VaadinSession.getCurrent().setAttribute(Roles.CURRENTUSER, null);

                } catch (Exception ex) {
                    Logger.getLogger(RegWeiterVertriebler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                    Notification.show(FEHLER, "Ihre Daten konnten nicht gespeichert werden: " + ex.getMessage(), Notification.Type.ERROR_MESSAGE);
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

