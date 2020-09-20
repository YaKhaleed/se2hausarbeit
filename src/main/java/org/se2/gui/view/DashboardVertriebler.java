package org.se2.gui.view;

import org.se2.ai.control.ToogleRouter;
import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.model.dao.VertrieblerDAO;
import org.se2.ai.model.entities.Vertriebler;
import org.se2.ai.model.entities.Benutzer;
//import org.se2.gui.components.BenachrichtigungVertriebler;
import org.se2.gui.components.BenachrichtigungVertriebler;
import org.se2.gui.components.KontoVerwalten;
import org.se2.gui.components.ProfilVertrieblerVerwalten;
import org.se2.gui.components.TopPanel;
import org.se2.gui.ui.MyUI;
import org.se2.services.util.Roles;
//import org.se2.services.util.SearchArbeitgeberServiceMitBewerbung;
//import org.se2.services.util.SearchArbeitgeberServiceOhneBewerbung;
import org.se2.services.util.Views;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

/**
 * @author zmorin2s
 */

public class DashboardVertriebler extends VerticalLayout implements View {
    public void setUp() {

        Benutzer user = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);
        this.addComponent(new TopPanel(user));

        //HorizontalLayout horizon = new HorizontalLayout();
        //Vertriebler a = VertrieblerDAO.getInstance().getVertriebler(user.getEmail());
        //SearchArbeitgeberServiceOhneBewerbung service = new SearchArbeitgeberServiceOhneBewerbung(a);
        //ComboBox<String> search = new ComboBox<>();

        /*
        try {
            if (ToogleRouter.isEnabled("bewerbung")) {
                search.setPlaceholder("Bewerber ");
                search.setWidth("400px");
                //SearchArbeitgeberServiceMitBewerbung servicea = new SearchArbeitgeberServiceMitBewerbung(a);
               // search.setDataProvider(servicea::fetch, servicea::count);
                horizon.addComponent(search);


                ComboBox<String> bewerber = new ComboBox<>();
                bewerber.setPlaceholder("Stellenanzeige");
                bewerber.setWidth("400px");
                //bewerber.setDataProvider(service::fetch, service::count);
                horizon.addComponent(bewerber);
            } else {
                search.setPlaceholder("(Stellenanzeige) ");
                search.setWidth("800px");
                //search.setDataProvider(service::fetch, service::count);
                horizon.addComponent(search);

            }



            horizon.setComponentAlignment(search, Alignment.MIDDLE_LEFT);



            final Button buttonsearch = new Button("Suche");
            buttonsearch.setWidth("200px");
            horizon.addComponent(buttonsearch);
            horizon.setComponentAlignment(buttonsearch, Alignment.MIDDLE_RIGHT);

         */

            //this.addComponent(horizon);
            //this.setComponentAlignment(horizon, Alignment.MIDDLE_CENTER);
            // Create the accordion
             Accordion accordion = new Accordion();
             Layout tab1 = new VerticalLayout();
             HorizontalLayout top = new HorizontalLayout();
            Label news = new Label("News");
            top.addComponent(news);
            news.setWidth("1100px");


            Button add = new Button("Autoanzeige erstellen");
            top.addComponent(add);
            add.addClickListener(clickEvent -> {
                ((MyUI) UI.getCurrent()).setBenutzer(user);
                UI.getCurrent().getSession().setAttribute(Roles.CURRENTUSER, user);
                UI.getCurrent().getNavigator().navigateTo(Views.AUTOANZEIGEERSTELLEN);
            });
            tab1.addComponent(top);
           /*
            if (ToogleRouter.isEnabled("bewerbung")) {

                Label n = new Label("Neue Bewerbungen sind eingegangen");
                tab1.addComponent(n);

            }
*/
            accordion.addTab(tab1, "Erstellte Autoanzeigen");




            final Layout tab2 = new ProfilVertrieblerVerwalten(user);
            accordion.addTab(tab2, "Profil verwalten");

           final Layout tab3 = new BenachrichtigungVertriebler(user);
            accordion.addTab(tab3, "Benachrichtigungen");

            final Layout tab4 = new KontoVerwalten(user);
            accordion.addTab(tab4, "Konto");

            accordion.setWidth("1200px");
            this.addComponent(accordion);
            this.setComponentAlignment(accordion, Alignment.MIDDLE_CENTER);
        //} catch (DatabaseException databaseException) {
          //  databaseException.printStackTrace();
        //}
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setUp();

    }
}

