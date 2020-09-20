package org.se2.gui.components;

import org.se2.ai.model.DTO.ReservierungDTO;
import org.se2.ai.model.entities.Kunde;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.se2.ai.model.factories.ReservierungsFactory;

import java.util.List;

/**
 * @author zmorin2s
 */

public class ReservierungKunde extends VerticalLayout {
    public ReservierungKunde(Kunde k) {
        VerticalLayout content = new VerticalLayout();


        List<ReservierungDTO> list = new ReservierungsFactory().getListReservierungKunde(k);
        int n;
        if (list.size() < 3) n = list.size();
        else n = 3;
        for (int i = 0; i < n; i++) {
            ReservierungDTO b = list.get(i);
            Label a = new Label("Ihre Reservierung für das Auto '" + b.getAutoanzeige().getTitel() +
                    "' wurde " + b.getStatus());
            content.addComponent(a);
        }
        Label reservierung = new Label("<b> Aktueller Stand deiner Reservierung </b>", ContentMode.HTML);
        content.addComponent(reservierung);
        for (int i = 0; i < list.size(); i++) {
            ReservierungDTO b = list.get(i);
            Label a = new Label((i + 1) + ". " +
                    b.getAutoanzeige().getTitel() + " - " +
                    b.getVertriebler().getName() + " - " +
                    b.getStatus());
            content.addComponent(a);
        }
        this.addComponent(content);
        this.setComponentAlignment(content, Alignment.MIDDLE_CENTER);


    }
}

