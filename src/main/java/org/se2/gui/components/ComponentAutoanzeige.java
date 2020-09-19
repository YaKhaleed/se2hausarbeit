package org.se2.gui.components;

import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.model.dao.AnforderungDAO;
//import org.se2.ai.model.DTO.Anforderung;
import org.se2.ai.model.DTO.AutoanzeigeDTO;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zmorin2s
 */

public class ComponentAutoanzeige {

    public VerticalLayout getAutoanzeigeComp(AutoanzeigeDTO autoanzeige) {
        VerticalLayout content = new VerticalLayout();
        HorizontalLayout top = new HorizontalLayout();

        VerticalLayout titel = new VerticalLayout();
        Label jt = new Label(autoanzeige.getTitel());
        jt.setWidth("600px");
        titel.addComponent(jt);

        Label ju = new Label(autoanzeige.getVertrieblerName() + " - ");// + autoanzeige.getOrt());
        ju.setWidth("600px");
        titel.addComponent(ju);

        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath +
                "/Image/Unknown_profil.png"));
        Image profilbild = new Image("", resource);

        VerticalLayout right = new VerticalLayout();
        right.addComponent(profilbild);
        right.setComponentAlignment(profilbild, Alignment.TOP_CENTER);
        right.addComponent(new Label(autoanzeige.getStatus()));
        right.setComponentAlignment(profilbild, Alignment.TOP_CENTER);
        right.addComponent(new Label(autoanzeige.getDatum().toString()));
        Button vertriebler = new Button(VaadinIcons.HOME);


        top.addComponent(titel);
        top.addComponent(right);
        top.addComponent(vertriebler);
        top.setComponentAlignment(vertriebler, Alignment.MIDDLE_CENTER);
        top.setHeight("175px");

        content.addComponent(top);
        content.setComponentAlignment(top, Alignment.TOP_CENTER);


        TextArea beschreibung = new TextArea("Autoanzeigenbeschreibung");
        beschreibung.setValue(autoanzeige.getBeschreibung());
        beschreibung.setReadOnly(true);
        beschreibung.setHeight("150px");
        beschreibung.setWidth("700px");
        content.addComponent(beschreibung);
        content.setComponentAlignment(beschreibung, Alignment.TOP_CENTER);

        /*
        TextArea anforderung = new TextArea("Anforderungen");
        anforderung.setReadOnly(true);
        try {
            List<Anforderung> a = AnforderungDAO.getInstance().getAnforderungForStellenanzeige(autoanzeige.getStellenanzeigeID());
            StringBuilder print = new StringBuilder();
            for (int i = 0; i < a.size(); i++) {
                print.append(a.get(i).getAnforderung());
                print.append("\n");
            }
            anforderung.setValue(String.valueOf(print));
        } catch (DatabaseException e) {
            Logger.getLogger(ComponentJobAusschreibung.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        anforderung.setHeight("150px");
        anforderung.setWidth("700px");
        content.addComponent(anforderung);
        content.setComponentAlignment(anforderung, Alignment.TOP_CENTER);

         */
        return content;
    }
}

