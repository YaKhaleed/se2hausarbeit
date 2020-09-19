package org.se2.gui.components;
/*
import org.se2.ai.control.ToogleRouter;
import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.model.dao.VertrieblerDAO;
import org.se2.ai.model.DTO.ReservierungDTO;
import org.se2.ai.model.entities.Vertriebler;
import org.se2.ai.model.entities.Benutzer;
import org.se2.gui.windows.InforBewerber;
import org.se2.gui.windows.ProfilStudent;
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


        Label titelstellenanzeige = new Label("<b> Ihre Stellenanzeigen </b>", ContentMode.HTML);
        content.addComponent(titelstellenanzeige);

        if (!user.getRole().equals("admin")) {

            List<StellenanzeigeDTO> jobangebot = new ProfilArbeitgeberControl().getStellenanzeige(ArbeitgeberDAO.getInstance().getArbeitgeber(user.getEmail()).getUnternehmen());
            for (int i = 0; i < jobangebot.size(); i++) {
                HorizontalLayout joblayout = new HorizontalLayout();
                Label job = new Label((i + 1) + "." + jobangebot.get(i).getTitel() + " - " + jobangebot.get(i).getStatus());
                Button jobangebotbearbeiten = new Button(VaadinIcons.PENCIL);

                int finalI = i;
                jobangebotbearbeiten.addClickListener(clickEvent -> {
                    StellenanzeigeDTO stellenanzeigeDTO = StellenanzeigeDAO.getInstance().getStellenanzeige(jobangebot.get(finalI).getTitel(), jobangebot.get(finalI).getBeschreibung(), jobangebot.get(finalI).getOrt(), jobangebot.get(finalI).getStatus());
                    UI.getCurrent().addWindow(new Jobangebotbearbeiten(stellenanzeigeDTO));
                });
                joblayout.addComponent(job);
                joblayout.addComponent(jobangebotbearbeiten);
                content.addComponent(joblayout);
            }
        }
        this.addComponent(content);
    }
}
*/
