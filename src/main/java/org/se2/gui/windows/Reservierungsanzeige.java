package org.se2.gui.windows;

/**
 * @author qthi2s
 */

import com.vaadin.ui.*;
import org.se2.ai.control.AutoanzeigeControl;
import org.se2.ai.control.ToogleRouter;
import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.model.DTO.AutoanzeigeDTO;
import org.se2.ai.model.entities.Autoanzeige;
import org.se2.gui.components.ComponentAutoanzeige;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Reservierungsanzeige extends Window {

    public Reservierungsanzeige(Autoanzeige Autoanzeige) {
        center();
        AutoanzeigeDTO autoanzeige = new AutoanzeigeControl().get(Autoanzeige.getAutoanzeigenID());

        VerticalLayout content = new ComponentAutoanzeige().getAutoanzeigeComp(autoanzeige);


        HorizontalLayout button = new HorizontalLayout();
        button.setWidth("800px");
        Button zuruck = new Button("Zurück");
        zuruck.setWidth("150px");
        zuruck.addClickListener(clickEvent -> close());
        button.addComponent(zuruck);
        button.setComponentAlignment(zuruck, Alignment.MIDDLE_LEFT);

        Button reservieren = new Button("Jetzt Reservieren!");
        reservieren.addClickListener(clickEvent -> {
            ReservierungsWindow window = new ReservierungsWindow (Autoanzeige);
            UI.getCurrent().addWindow(window);
        });
        button.addComponent(reservieren);
        button.setComponentAlignment(reservieren, Alignment.MIDDLE_RIGHT);
        try {
            if (ToogleRouter.isEnabled("reservieren")) {

            }
        } catch (DatabaseException e) {
            Logger.getLogger(Reservierungsanzeige.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        content.addComponent(button);
        content.setComponentAlignment(button, Alignment.TOP_CENTER);

        this.setContent(content);


    }


}
