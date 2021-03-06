package org.se2.gui.view;

import org.se2.ai.control.RegistrationControl;
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
        Label label = new Label("<b> Jetzt können Sie nach Autos suchen und diese reservieren! </b>", ContentMode.HTML);
        label.addStyleName("mytitle");
        label.addStyleName(ValoTheme.LABEL_H1);
        haupt.addComponent(label);
        haupt.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
        final ComboBox<String> userAnrede = new AnredeField();
        haupt.addComponent(userAnrede);

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


        final Label label2 = new Label("&nbsp;", ContentMode.HTML);
        this.addComponent(label2);

        final Button ubermitteln = new Button();
        ubermitteln.setCaption("Übermitteln");
        ubermitteln.setWidth(WIDTH);

        binder.forField(vorname).asRequired("Sie müssen Ihre Vorname(n) eingeben")
                .withValidator(new StringLengthValidator("Vorname(n) zu kurz", 3, 30))
                .bind(Benutzer::getRolle, Benutzer::setRolle);
        binder.forField(nachname).asRequired("Sie müssen Ihre Nachname(n) eingeben")
                .withValidator(new StringLengthValidator("Nachname(n) zu kurz", 3, 30))
                .bind(Benutzer::getRolle, Benutzer::setRolle);



        ubermitteln.addClickListener(event -> {
            binder.validate();
            if (binder.validate().isOk()) {


                String anrede = userAnrede.getValue();
                String uservorname = vorname.getValue();
                String username = nachname.getValue();

                // instance of control
                RegistrationControl r = new RegistrationControl();
                KundeDTO kunde = new KundeDTO();
                try {
                    kunde.setAnrede(anrede);
                    kunde.setVorname(uservorname);
                    kunde.setNachname(username);
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
