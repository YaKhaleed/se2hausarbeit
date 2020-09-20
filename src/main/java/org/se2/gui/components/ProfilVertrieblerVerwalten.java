package org.se2.gui.components;

import org.se2.ai.control.ProfilVertrieblerControl;
import org.se2.ai.model.dao.AdresseDAO;
import org.se2.ai.model.dao.VertrieblerDAO;
import org.se2.ai.model.DTO.Adresse;
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
import org.vaadin.textfieldformatter.NumeralFieldFormatter;

import java.io.File;
import java.util.List;

public class ProfilVertrieblerVerwalten extends ProfilVerwalten {
    private static final String PX1000 = "1000px";

    public ProfilVertrieblerVerwalten(Benutzer user) {
        super(user);
        Vertriebler current = VertrieblerDAO.getInstance().getVertriebler(user.getEmail());
        VerticalLayout main = new VerticalLayout();
        HorizontalLayout logoandname = new HorizontalLayout();

        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath +
                "/Image/Unknown_profil.png"));
        Image logo = new Image("", resource);
        logoandname.addComponent(logo);
        //Uploader x = new Uploader();
        //x.init("basic");

        //Statt Unternehmen, mit Autoanzeige arbeiten
        TextField name = new TextField();
        name.setPlaceholder("Name");
        if (!isAdmin) {
            String nameVertriebler = current.getName();
            if (nameVertriebler.length() != 0) name.setValue(nameVertriebler);
        }
        name.setWidth("580px");
        name.setHeight("50px");
        logoandname.addComponent(name);
        logoandname.setComponentAlignment(name, Alignment.MIDDLE_CENTER);


        //logoandname.addComponent(x);
        //logoandname.setComponentAlignment(x, Alignment.MIDDLE_RIGHT);

        main.addComponent(logoandname);

        HorizontalLayout beschreibungandkontakt = new HorizontalLayout();
        beschreibungandkontakt.setWidth(PX1000);


        // Create the beschreibungDesVertrieblersName

        TextArea beschreibungDesVertrieblersName = new TextArea("Kurze Beschreibung des Unternehmens");

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


        TextArea stellenanzeige = new TextArea("Stellenanzeige");
        stellenanzeige.setPlaceholder("Stellenanzeige");
        if (!isAdmin) {
            StringBuilder print = new StringBuilder();
            ProfilVertrieblerControl profilVertrieblerControl = new ProfilVertrieblerControl();
            List<AutoanzeigeDTO> autoanzeige = profilVertrieblerControl.getStellenanzeige(current.getName());
            if (autoanzeige != null) {
                for (int i = 0; i < autoanzeige.size(); i++) {
                    AutoanzeigeDTO s = autoanzeige.get(i);
                    print.append(i + 1).append(". ").append(s.getTitel()).append("\n");
                }
            }
            stellenanzeige.setValue(String.valueOf(print));
        }
        stellenanzeige.setReadOnly(true);
        stellenanzeige.setWidth("650px");
        stellenanzeige.setHeight("240px");
        bottom.addComponent(stellenanzeige);
        bottom.setComponentAlignment(stellenanzeige, Alignment.MIDDLE_LEFT);


        /*
        VerticalLayout kontaktandadresse = new VerticalLayout();
        kontaktandadresse.setWidth("300px");

        TextArea kontakte = new TextArea("Kontakt");
        if (!isAdmin) kontakte.setValue(current.getTelefonnummer());
        kontakte.setHeight("70px");
        kontakte.setWidth("300px");
        kontaktandadresse.addComponent(kontakte);

        Label ad = new Label("Adresse");
        kontaktandadresse.addComponent(ad);


        TextField strasse = new TextField();
        TextField hausNummer = new TextField();
        TextField plz = new TextField();
        new NumeralFieldFormatter("", "", 1).extend(plz);
        TextField ort = new TextField();


        HorizontalLayout strassehnr = new HorizontalLayout();

        strasse.setPlaceholder("Strasse");

        if (!isAdmin) {
            Adresse addrUnternehmen = AdresseDAO.getInstance().getAdresse(current.getId());
            String strasseUnternehmen = addrUnternehmen.getStrasse();
            if (strasseUnternehmen.length() != 0) strasse.setValue(strasseUnternehmen);
            String strNr = addrUnternehmen.getHausnummer();
            String ortUnt = addrUnternehmen.getOrt();
            if (strNr.length() != 0) hausNummer.setValue(strNr);
            int plzUnt = addrUnternehmen.getPlz();
            if (plzUnt != 0) plz.setValue(String.valueOf(plzUnt));
            if (ortUnt.length() != 0) ort.setValue(ortUnt);
            else ort.setValue("Bitte eingeben!");

        }
        strasse.setWidth("150px");
        strasse.setHeight("50px");
        strassehnr.addComponent(strasse);


        hausNummer.setPlaceholder("Hausnummer");
        hausNummer.setWidth("130px");
        hausNummer.setHeight("50px");
        strassehnr.addComponent(hausNummer);


        HorizontalLayout plzort = new HorizontalLayout();
        plz.setPlaceholder("Postleitzahl");
        plz.setWidth("150px");
        plz.setHeight("50px");

        ort.setPlaceholder("Ort ");

        ort.setWidth("130px");
        ort.setHeight("50px");

        plzort.addComponent(plz);
        plzort.addComponent(ort);

        kontaktandadresse.addComponent(strassehnr);
        kontaktandadresse.addComponent(plzort);
        // end split


        bottom.addComponent(kontaktandadresse);
        bottom.setComponentAlignment(kontaktandadresse, Alignment.TOP_RIGHT);

         */
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
            //vertriebler.setTelefonnummer(telefonNummer);
            vertriebler.setVertrieblerID(current.getVertrieblerID());
            //company.setAdresse(new Adresse(strasse.getValue(), Integer.parseInt(plz.getValue()), hausNummer.getValue(), ort.getValue()));
            //vertriebler.getAdresse().setAdresseID(user.getAdresseId());


            // TODO: implement unnecessary updates when nothing has changed
            boolean isokay = pc.updateVertrieblerprofil(vertriebler);
            if (isokay) {
                BestaetigenReg confirm = new BestaetigenReg("Die Änderung wurde gespeichert", Views.DASHBOARDA);
                UI.getCurrent().addWindow(confirm);
            }
        });

    }
}

