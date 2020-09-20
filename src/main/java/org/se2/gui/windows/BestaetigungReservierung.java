package org.se2.gui.windows;

import com.vaadin.ui.*;
import org.se2.ai.control.ReservierungControl;
import org.se2.ai.model.DTO.AutoanzeigeDTO;
import org.se2.ai.model.DTO.ReservierungDTO;
import org.se2.ai.model.entities.Kunde;

/**
 * @author qthi2s
 */


public class BestaetigungReservierung extends Window {

    public BestaetigungReservierung(AutoanzeigeDTO a, ReservierungDTO r, Kunde k) {
        center();

        //Some basic content for the window
        VerticalLayout content = new VerticalLayout();
        content.addComponent(new com.vaadin.ui.Label("Sind Sie sicher, dass Sie die Reservierung \t jetzt abschicken möchten? "));
        content.setMargin(true);
        setContent(content);

        HorizontalLayout janein = new HorizontalLayout();
        janein.setWidth("550px");

        com.vaadin.ui.Button nein = new com.vaadin.ui.Button("Nein");
        janein.addComponent(nein);
        nein.addClickListener(clickEvent -> close());
        janein.setComponentAlignment(nein, Alignment.MIDDLE_LEFT);

        com.vaadin.ui.Button ja = new com.vaadin.ui.Button("Ja");
        janein.addComponent(ja);
        ja.addClickListener(clickEvent -> {
            new ReservierungControl().createReservierung(a, r, k);
            com.vaadin.ui.Window confirm = new com.vaadin.ui.Window();
            confirm.setContent(new Label("Super! Die Reservierung wurde verschickt."));
            confirm.center();
            confirm.setWidth("550px");
            UI.getCurrent().addWindow(confirm);
            close();
        });
        janein.setComponentAlignment(ja, Alignment.MIDDLE_RIGHT);

        content.addComponent(janein);
        content.setComponentAlignment(janein, Alignment.BOTTOM_CENTER);
    }
}
