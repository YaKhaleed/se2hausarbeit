package org.se2.gui.components;

import org.se2.ai.control.ProfilKundeControl;
import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.model.dao.KundeDAO;

import org.se2.ai.model.entities.Benutzer;
import org.se2.ai.model.entities.Kunde;
//import org.se2.services.util.ImageUploader;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

import java.io.File;


public class ProfilKundeVerwalten extends ProfilVerwalten {
    static final String PX_700 = "700px";

    public ProfilKundeVerwalten(Benutzer user) throws DatabaseException {
        super(user);
        boolean isadmin = user.getRolle().equals("admin");
        //email == benutzer_id vllt ncoh ändern
        Kunde current = KundeDAO.getInstance().getKunde(user.getEmail());
        ProfilKundeControl c = new ProfilKundeControl();
        HorizontalLayout horizon1 = new HorizontalLayout();
        VerticalLayout vartical1 = new VerticalLayout();
        TextField name = new TextField();
        name.setPlaceholder("Vorname Nachname");
        if (!isadmin) {
            String kundenname = current.getVorname() + " " + current.getNachname();
            if (kundenname.length() != 0) {
                name.setValue(kundenname);
            }
        }
        name.setWidth(PX_700);
        name.setHeight("40px");
        vartical1.addComponent(name);
        vartical1.setComponentAlignment(name, Alignment.MIDDLE_CENTER);

        HorizontalLayout horizon2 = new HorizontalLayout();
        horizon2.setHeight("75px");
        horizon2.setWidth(PX_700);


        horizon1.addComponent(vartical1);
        horizon1.setComponentAlignment(vartical1, Alignment.TOP_LEFT);

        VerticalLayout vertical2 = new VerticalLayout();
        vertical2.setHeight("525px");


        horizon1.addComponent(vertical2);
        horizon1.setComponentAlignment(vertical2, Alignment.TOP_RIGHT);
        horizon1.setWidth("1100px");
        this.addComponent(horizon1);
        this.setComponentAlignment(horizon1, Alignment.TOP_CENTER);

        Button speichern = new Button("Speichern");
        speichern.addClickListener(clickEvent -> {
        });
        speichern.addClickListener(event -> {
            ProfilKundeControl psc = new ProfilKundeControl();
            String kundenName = name.getValue(); // split into vor/nachname

        });
        this.addComponent(speichern);
        this.setComponentAlignment(speichern, Alignment.BOTTOM_CENTER);

        this.setWidth("1150px");
        this.setHeight("600px");

    }

}
