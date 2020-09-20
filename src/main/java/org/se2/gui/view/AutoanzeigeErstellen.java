package org.se2.gui.view;

//import org.se2.ai.model.DTO.Anforderung;
import org.se2.ai.model.entities.Benutzer;
import org.se2.ai.model.entities.Autoanzeige;
import org.se2.gui.components.TopPanel;
import org.se2.gui.windows.BestaetigungAutoanzeige;
import org.se2.services.util.Roles;
import org.se2.services.util.Views;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zmorin2s
 */
public class AutoanzeigeErstellen extends VerticalLayout implements View {
    private static final String WIDTH = "750px";
    private static final String WIDTHB = "150px";
    final transient Benutzer user = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);

    public void setUp() {

        this.addComponent(new TopPanel(user));
        Panel main = new Panel();
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("1000px");

        VerticalLayout mainlayout = new VerticalLayout();
        mainlayout.setWidth("800px");
        Label label = new Label("<b> Autoanzeige hochladen </b>", ContentMode.HTML);
        label.addStyleName("mytitle");
        label.addStyleName(ValoTheme.LABEL_H1);
        mainlayout.addComponent(label);
        TextField titel = new TextField("Titel");
        titel.setWidth(WIDTH);
        mainlayout.addComponent(titel);


        TextArea beschreibung = new TextArea("Beschreibung");
        beschreibung.setWidth(WIDTH);
        mainlayout.addComponent(beschreibung);

        /*
        TextField ort = new TextField("Ort");
        ort.setWidth(WIDTH);
        mainlayout.addComponent(ort);

         */

        /*
        TextArea anforderung = new TextArea("Anforderung");
        anforderung.setWidth(WIDTH);
        mainlayout.addComponent(anforderung);

         */

        HorizontalLayout datestatus = new HorizontalLayout();
        datestatus.setWidth(WIDTH);

        // Create a DateField with the default style
        DateField date = new DateField("Datum");
        date.setWidth("350px");
        datestatus.addComponent(date);

        ComboBox status = new ComboBox("Status");
        status.setItems("Offen", "Reserviert", "Geschlossen");
        status.setWidth("350px");
        datestatus.addComponent(status);
        datestatus.setComponentAlignment(status, Alignment.MIDDLE_RIGHT);
        mainlayout.addComponent(datestatus);

        HorizontalLayout bottom = new HorizontalLayout();
        bottom.setWidth("900px");
        Button zuruck = new Button("Zurück");
        zuruck.addClickListener(clickEvent -> UI.getCurrent().getNavigator().navigateTo(Views.DASHBOARDA));
        zuruck.setWidth(WIDTHB);
        bottom.addComponent(zuruck);
        bottom.setComponentAlignment(zuruck, Alignment.MIDDLE_LEFT);

        Button abschicken = new Button("Abschicken");
        abschicken.addClickListener(clickEvent -> {
            Autoanzeige a = new Autoanzeige();
            a.setTitel(titel.getValue());
            a.setBeschreibung(beschreibung.getValue());
            a.setDatum(date.getValue());
            a.setStatus((String) status.getValue());

            BestaetigungAutoanzeige confirm = new BestaetigungAutoanzeige(a);


            UI.getCurrent().addWindow(confirm);

        });
        abschicken.setWidth(WIDTHB);
        bottom.addComponent(abschicken);
        bottom.setComponentAlignment(abschicken, Alignment.MIDDLE_RIGHT);

        layout.addComponent(mainlayout);
        layout.setComponentAlignment(mainlayout, Alignment.MIDDLE_CENTER);
        layout.addComponent(bottom);
        layout.setComponentAlignment(bottom, Alignment.BOTTOM_CENTER);

        main.setContent(layout);
        main.setSizeUndefined();
        this.addComponent(main);
        this.setComponentAlignment(main, Alignment.MIDDLE_CENTER);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (user == null) {
            UI.getCurrent().getNavigator().navigateTo(Views.STARTSEITE);

        } else {
            this.setUp();
        }
    }
}

