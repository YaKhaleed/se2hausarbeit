package org.se2.gui.view;


import org.se2.ai.model.entities.Benutzer;
import org.se2.gui.components.BenachrichtigungVertriebler;
import org.se2.gui.components.KontoVerwalten;
import org.se2.gui.components.ProfilVertrieblerVerwalten;
import org.se2.gui.components.TopPanel;
import org.se2.gui.ui.MyUI;
import org.se2.services.util.Roles;

import org.se2.services.util.Views;

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

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setUp();

    }
}

