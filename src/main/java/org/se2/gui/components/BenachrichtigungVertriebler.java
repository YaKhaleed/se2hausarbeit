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

public class BenachrichtigungVertriebler extends VerticalLayout {
    public BenachrichtigungVertriebler(Benutzer user) {
        VerticalLayout content = new VerticalLayout();
        /*
        try {

            if (ToogleRouter.isEnabled("bewerbung")) {
                Label titelbewerbung = new Label("<b> Ihre eingegangenen Bewerbungen </b>", ContentMode.HTML);
                content.addComponent(titelbewerbung);
                Vertriebler a = VertrieblerDAO.getInstance().getVertriebler(user.getEmail());
                List<ReservierungDTO> bewerbungs = ReservierungDTO.getInstance().getListBewerbungForArbeitgeber(a);

                for (int i = 0; i < bewerbungs.size(); i++) {
                    HorizontalLayout layout = new HorizontalLayout();
                    BewerbungCollAtHBRSDTO bewerbung = bewerbungs.get(i);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
                    String formattedString = bewerbung.getDatum().format(formatter);
                    Label label = new Label((i + 1) + ". " +
                            bewerbung.getStudent().getNachname() + ", " +
                            bewerbung.getStudent().getVorname() + " - " +
                            bewerbung.getStellenanzeige().getTitel() + " - " +
                            formattedString
                    );
                    Student student = bewerbung.getStudent();
                    layout.addComponent(label);
                    layout.addLayoutClickListener(layoutClickEvent -> {
                        ProfilStudent window = null;
                        window = new ProfilStudent(student, bewerbung);
                        UI.getCurrent().addWindow(window);
                    });
                    Button infor = new Button(VaadinIcons.INFO);
                    infor.addClickListener(clickEvent -> {
                        InforBewerber window = new InforBewerber(bewerbung);
                        UI.getCurrent().addWindow(window);
                    });
                    layout.addComponent(infor);
                    content.addComponent(layout);
                }
            }
        } catch (DatabaseException e) {
            Logger.getLogger(BenachrichtigungVertriebler.class.getName()).log(Level.SEVERE, e.getReason(), e);
        }
        */


        Label titelautoanzeige = new Label("<b> Ihre Autoanzeige </b>", ContentMode.HTML);
        content.addComponent(titelautoanzeige);

        if (!user.getRolle().equals("admin")) {

            List<AutoanzeigeDTO> anzeige = new ProfilVertrieblerControl().getAutoanzeige(VertrieblerDAO.getInstance().getVertriebler(user.getEmail()).getName());
            for (int i = 0; i < anzeige.size(); i++) {
                HorizontalLayout anzeigelayout = new HorizontalLayout();
                Label job = new Label((i + 1) + "." + anzeige.get(i).getTitel() + " - " + anzeige.get(i).getStatus());
                Button reservierung = new Button(VaadinIcons.PENCIL);

                int finalI = i;
                reservierung.addClickListener(clickEvent -> {
                    AutoanzeigeDTO stellenanzeigeDTO = AutoanzeigeDAO.getInstance().getAutoanzeige(anzeige.get(finalI).getTitel());
                    //UI.getCurrent().addWindow(new Jobangebotbearbeiten(stellenanzeigeDTO));
                });
                anzeigelayout.addComponent(job);
                anzeigelayout.addComponent(reservierung);
                content.addComponent(anzeigelayout);
            }
        }
        this.addComponent(content);
    }
}

