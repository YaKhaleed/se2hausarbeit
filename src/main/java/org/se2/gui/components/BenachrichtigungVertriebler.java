package org.se2.gui.components;

import org.se2.ai.control.ProfilVertrieblerControl;
import org.se2.ai.control.ToogleRouter;
import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.model.DTO.AutoanzeigeDTO;
import org.se2.ai.model.dao.AutoanzeigeDAO;
import org.se2.ai.model.dao.VertrieblerDAO;
import org.se2.ai.model.DTO.ReservierungDTO;
import org.se2.ai.model.entities.Vertriebler;
import org.se2.ai.model.entities.Benutzer;
//import org.se2.gui.windows.InforBewerber;
//import org.se2.gui.windows.ProfilStudent;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 @author zmorin2s
 */

public class BenachrichtigungVertriebler extends VerticalLayout {
    public BenachrichtigungVertriebler(Benutzer user) {
        VerticalLayout content = new VerticalLayout();


        Label titelautoanzeige = new Label("<b> Ihre Autoanzeige </b>", ContentMode.HTML);
        content.addComponent(titelautoanzeige);

        if (!user.getRolle().equals("admin")) {

            List<AutoanzeigeDTO> anzeige = new ProfilVertrieblerControl().getAutoanzeige(VertrieblerDAO.getInstance().getVertriebler(user.getEmail()).getName());
            for (int i = 0; i < anzeige.size(); i++) {
                HorizontalLayout anzeigelayout = new HorizontalLayout();
                Label anzeigeLabel = new Label((i + 1) + "." + anzeige.get(i).getTitel() + " - " + anzeige.get(i).getStatus());
                Button reservierung = new Button(VaadinIcons.PENCIL);

                int finalI = i;
                reservierung.addClickListener(clickEvent -> {
                    AutoanzeigeDTO autoanzeigeDTO = AutoanzeigeDAO.getInstance().getAutoanzeige(anzeige.get(finalI).getTitel());
                });
                anzeigelayout.addComponent(anzeigeLabel);
                anzeigelayout.addComponent(reservierung);
                content.addComponent(anzeigelayout);
            }
        }
        this.addComponent(content);
    }
}

