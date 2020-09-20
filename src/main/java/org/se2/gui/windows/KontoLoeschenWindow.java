package org.se2.gui.windows;

import com.vaadin.event.ShortcutAction;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.se2.ai.control.KontoControl;
import org.se2.ai.control.LoginControl;
import org.se2.ai.control.exceptions.DatabaseException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author qthi2s
 */

public class KontoLoeschenWindow extends Window {

    public KontoLoeschenWindow(String mail, String password) {
        super(); //Set Window caption
        center();

        VerticalLayout content = new VerticalLayout();
        Label top = new Label("Sind Sie sicher, dass Sie Ihr Konto löschen wollen?");
        content.addComponent(top);
        content.setComponentAlignment(top, Alignment.TOP_CENTER);

        Label label = new Label("Schade, dass Sie nicht mehr ein Mitglied sein wollen.");
        content.addComponent(label);

        HorizontalLayout janein = new HorizontalLayout();
        janein.setWidth("550px");

        Button nein = new Button("Nein");
        janein.addComponent(nein);
        janein.setComponentAlignment(nein, Alignment.MIDDLE_LEFT);

        Button ja = new Button("Ja");
        janein.addComponent(ja);
        janein.setComponentAlignment(ja, Alignment.MIDDLE_RIGHT);

        Label platz = new Label("&nbsp", ContentMode.HTML);
        content.addComponent(platz);

        content.addComponent(janein);
        content.setComponentAlignment(janein, Alignment.MIDDLE_CENTER);

        content.setMargin(true);
        setContent(content);

        ja.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        nein.addClickListener(clickEvent -> close());
        ja.addClickListener(clickEvent -> {
            KontoControl kc = new KontoControl();
            try {
                kc.deleteKonto(mail, password);
                LoginControl.logoutbenutzer();
                close();
            } catch (DatabaseException ex) {
                Logger.getLogger(KontoLoeschenWindow.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }



}
