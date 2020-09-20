package org.se2.gui.view;

import org.se2.ai.control.SucheProxy;
import org.se2.ai.control.ToogleRouter;
import org.se2.ai.control.exceptions.DatabaseException;
//import org.se2.ai.model.dao.SearchService;
import org.se2.ai.model.dao.KundeDAO;
import org.se2.ai.model.DTO.AutoanzeigeDTO;
import org.se2.ai.model.dao.SucheTitel;
import org.se2.ai.model.entities.Benutzer;
import org.se2.ai.model.entities.Kunde;
import org.se2.gui.components.ReservierungKunde;
import org.se2.gui.components.KontoVerwalten;
import org.se2.gui.components.ProfilKundeVerwalten;
import org.se2.gui.components.TopPanel;
import org.se2.services.util.Roles;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zmorin2s
 */

public class DashboardKunde extends KundeView {
    public void setUp() throws DatabaseException {
        Benutzer user = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);
        this.addComponent(new TopPanel(user));

        HorizontalLayout horizon = new HorizontalLayout();
        final ComboBox<String> autosuche = new ComboBox<>();
        autosuche.setPlaceholder("Suche nach Automarken deiner Wahl!");
        autosuche.setWidth("800px");
        SucheTitel searchService = new SucheTitel();
        autosuche.setDataProvider(searchService::fetch, searchService::count);
        horizon.addComponent(autosuche);
        horizon.setComponentAlignment(autosuche, Alignment.MIDDLE_LEFT);

        final Button buttonsearch = new Button("Autos finden!");
        buttonsearch.setWidth("200px");
        horizon.addComponent(buttonsearch);
        horizon.setComponentAlignment(buttonsearch, Alignment.MIDDLE_RIGHT);
        if (autosuche.getValue() == null) {
            this.addComponent(horizon);
            this.setComponentAlignment(horizon, Alignment.MIDDLE_CENTER);
            // Create the tabsheet
            final TabSheet tabsheet = new TabSheet();


            final VerticalLayout tab2 = new ProfilKundeVerwalten(user);
            tabsheet.addTab(tab2, "Profil verwalten");
            VerticalLayout tab3 = new VerticalLayout();



            if (ToogleRouter.isEnabled("reservierung")) {
                Kunde current = KundeDAO.getInstance().getKunde(user.getEmail());
                tab3 = new ReservierungKunde(current);
            }



            tabsheet.addTab(tab3, "Reservierungen");


            final VerticalLayout tab4 = new KontoVerwalten(user);
            tabsheet.addTab(tab4, "Konto");
            buttonsearch.addClickListener(clickEvent -> {
                this.removeComponent(tabsheet);
                List<AutoanzeigeDTO> liste = SucheProxy.getInstance().getAutoanzeigeListe(autosuche.getValue());
                Panel ergebnisse = new SucheSeite().printergebnis(liste);
                this.addComponent(ergebnisse);
            });

            tabsheet.setWidth("1200px");
            horizon.addComponent(tabsheet);
            horizon.setComponentAlignment(tabsheet, Alignment.MIDDLE_CENTER);
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (isLoggedIn()) {
            try {
                this.setUp();
            } catch (DatabaseException ex) {
                Logger.getLogger(DashboardKunde.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }


    }
}

