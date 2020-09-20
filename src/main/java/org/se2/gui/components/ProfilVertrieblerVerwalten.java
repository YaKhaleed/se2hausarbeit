package org.se2.gui.components;

import org.se2.ai.control.ProfilVertrieblerControl;
import org.se2.ai.model.dao.VertrieblerDAO;
import org.se2.ai.model.DTO.AutoanzeigeDTO;
import org.se2.ai.model.DTO.VertrieblerDTO;
import org.se2.ai.model.entities.Vertriebler;
import org.se2.ai.model.entities.Benutzer;
import org.se2.gui.windows.BestaetigenReg;
//import org.se2.services.util.Uploader;
import org.se2.services.util.Views;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

import java.io.File;
import java.util.List;

/**
 * @author zmorin2s
 */

public class ProfilVertrieblerVerwalten extends ProfilVerwalten {
    private static final String PX1000 = "1000px";

    public ProfilVertrieblerVerwalten(Benutzer user) {
        super(user);
        Vertriebler current = VertrieblerDAO.getInstance().getVertriebler(user.getEmail());
        VerticalLayout main = new VerticalLayout();


        //Statt Unternehmen, mit Autoanzeige arbeiten
        TextField name = new TextField();
        name.setPlaceholder("Name des Unternehmens");
        if (!isAdmin) {
            String nameVertriebler = current.getName();
            if (nameVertriebler.length() != 0) name.setValue(nameVertriebler);
        }
        name.setWidth("580px");
        name.setHeight("50px");


        HorizontalLayout beschreibungandkontakt = new HorizontalLayout();
        beschreibungandkontakt.setWidth(PX1000);


        // Create the beschreibungDesVertrieblersName

        TextArea beschreibungDesVertrieblersName = new TextArea("Kurze Beschreibung des Vertrieblers");

        if (!isAdmin) {
            String beschreibung = current.getName();
            if (beschreibung != null) {
                beschreibungDesVertrieblersName.setValue(current.getName());
            }
        }
        beschreibungDesVertrieblersName.setHeight("90px");
        beschreibungDesVertrieblersName.setWidth(PX1000);
        main.addComponent(beschreibungDesVertrieblersName);
        main.setComponentAlignment(beschreibungDesVertrieblersName, Alignment.TOP_LEFT);




        HorizontalLayout bottom = new HorizontalLayout();


        TextArea autoanzeigeText = new TextArea("Autoanzeige");
        autoanzeigeText.setPlaceholder("Autoanzeige");
        if (!isAdmin) {
            StringBuilder print = new StringBuilder();
            ProfilVertrieblerControl profilVertrieblerControl = new ProfilVertrieblerControl();
            List<AutoanzeigeDTO> autoanzeige = profilVertrieblerControl.getAutoanzeige(current.getName());
            if (autoanzeige != null) {
                for (int i = 0; i < autoanzeige.size(); i++) {
                    AutoanzeigeDTO s = autoanzeige.get(i);
                    print.append(i + 1).append(". ").append(s.getTitel()).append("\n");
                }
            }
            autoanzeigeText.setValue(String.valueOf(print));
        }
        autoanzeigeText.setReadOnly(true);
        autoanzeigeText.setWidth("650px");
        autoanzeigeText.setHeight("240px");
        bottom.addComponent(autoanzeigeText);
        bottom.setComponentAlignment(autoanzeigeText, Alignment.MIDDLE_LEFT);
        main.addComponent(bottom);


        Button speichern = new Button("Speichern");
        main.addComponent(speichern);
        main.setComponentAlignment(speichern, Alignment.TOP_CENTER);

        this.addComponent(main);
        this.setHeight("800px");
        this.setComponentAlignment(main, Alignment.TOP_CENTER);
        // TODO add validators/binders
        speichern.addClickListener(event -> {
            ProfilVertrieblerControl pc = new ProfilVertrieblerControl();
            String vertrieblerName = name.getValue();
            String beschreibungKurz = beschreibungDesVertrieblersName.getValue();
            //String telefonNummer = kontakte.getValue();


            VertrieblerDTO vertriebler = new VertrieblerDTO();
            //company.setBeschreibung(beschreibungKurz);
            vertriebler.setName(vertrieblerName);
            vertriebler.setVertrieblerID(current.getVertrieblerID());


            // TODO: implement unnecessary updates when nothing has changed
            boolean isokay = pc.updateVertrieblerprofil(vertriebler);
            if (isokay) {
                BestaetigenReg confirm = new BestaetigenReg("Die Änderung wurde gespeichert", Views.DASHBOARDA);
                UI.getCurrent().addWindow(confirm);
            }
        });

    }
}

