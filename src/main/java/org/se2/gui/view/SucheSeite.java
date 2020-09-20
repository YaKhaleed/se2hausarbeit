package org.se2.gui.view;

import org.se2.ai.control.Suche;
import org.se2.ai.model.dao.AutoanzeigeDAO;
import org.se2.ai.model.DTO.AutoanzeigeDTO;
import org.se2.ai.model.dao.SucheTitel;
import org.se2.ai.model.entities.Benutzer;
import org.se2.gui.components.TopPanel;
import org.se2.gui.ui.MyUI;
import org.se2.gui.windows.Reservierungsanzeige;
//import org.se2.gui.windows.StellenanzeigeK;
//import org.se2.services.util.OrtService;
import org.se2.services.util.Roles;
import org.se2.services.util.Views;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

import java.io.File;
import java.util.List;

/**
 * @author zmorin2s
 */

public class SucheSeite extends VerticalLayout implements View {

    private transient List<AutoanzeigeDTO> liste = null;

    public void setUp() {
        Benutzer user = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);
        this.addComponent(new TopPanel(user));

        setMargin(true);
        HorizontalLayout horizon = new HorizontalLayout();

        Button button = new Button("Autos finden", VaadinIcons.SEARCH);
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        ComboBox<String> search = new ComboBox<>();
        search.setPlaceholder("BMW, AUDI, VW, ... ");
        search.setWidth("500px");
       SucheTitel service = new SucheTitel();
        search.setDataProvider(service::fetch, service::count);


        horizon.addComponents(search, button);
        addComponent(horizon);


        setComponentAlignment(horizon, Alignment.MIDDLE_CENTER);
        horizon.setComponentAlignment(button, Alignment.BOTTOM_RIGHT);

        VerticalLayout show = new VerticalLayout();
        // Event Listener fÃ¼r den Suchen Button
        button.addClickListener(e -> {
            Panel sucheLayout = new Panel();
            String titel = search.getValue();
            if (titel != null) {
                    liste = Suche.getInstance().getAutoanzeigeListe(titel);
            }


            show.removeAllComponents();
            sucheLayout = printergebnis(liste);
            show.addComponent(sucheLayout);
            addComponent(show);
            setComponentAlignment(show, Alignment.MIDDLE_CENTER);


        });


    }

    public Panel printergebnis(List<AutoanzeigeDTO> liste) {
        VerticalLayout scrollableLayout = new VerticalLayout();
        Panel main = new Panel();
        if (!liste.isEmpty()) {
            for (AutoanzeigeDTO suche : liste) {
                HorizontalLayout article = new HorizontalLayout();
                VerticalLayout titelbeschreibung = new VerticalLayout();
                HorizontalLayout info = new HorizontalLayout();
                Label stitel = new Label(suche.getTitel(), ContentMode.TEXT);
                stitel.setWidth("400px");
                info.addComponent(stitel);

                Label svertriebler = new Label(suche.getVertrieblerName(), ContentMode.PREFORMATTED);
                info.addComponent(svertriebler);
                info.setComponentAlignment(svertriebler, Alignment.TOP_CENTER);
                svertriebler.setWidth("200px");

                Label sdatum = new Label(suche.getDatum().toString(), ContentMode.PREFORMATTED);
                info.addComponent(sdatum);
                info.setComponentAlignment(sdatum, Alignment.TOP_CENTER);
                sdatum.setWidth("100px");

                /*
                Label sort = new Label(suche.getOrt(), ContentMode.PREFORMATTED);
                info.addComponent(sort);
                info.setComponentAlignment(sort, Alignment.TOP_CENTER);
                sort.setWidth("175px");

                 */

                Label sstatus = new Label(suche.getStatus(), ContentMode.PREFORMATTED);
                info.addComponent(sstatus);
                info.setComponentAlignment(sstatus, Alignment.TOP_CENTER);
                sstatus.setWidth("75px");

                info.setHeight("60px");

                titelbeschreibung.addComponent(info);

                article.addComponent(titelbeschreibung);
                article.setComponentAlignment(titelbeschreibung, Alignment.TOP_CENTER);

                scrollableLayout.addComponent(article);
                AutoanzeigeDTO s = AutoanzeigeDAO.getInstance().getAutoanzeige(suche.getTitel());
                article.addLayoutClickListener(event -> {
                    Reservierungsanzeige window = new Reservierungsanzeige(s);
                    UI.getCurrent().addWindow(window);
                });

            }
        }
        main.setContent(scrollableLayout);
        return main;
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();

        if (user == null) {
            UI.getCurrent().getNavigator().navigateTo((Views.LOGIN));
        } else {
            this.setUp();

        }
    }

}

