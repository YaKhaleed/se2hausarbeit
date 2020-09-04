package org.se2.gui.windows;

import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.se2.ai.model.DTO.AutoanzeigeDTO;
import org.se2.ai.model.DTO.ReservierungDTO;
import org.se2.ai.model.dao.KundeDAO;
import org.se2.ai.model.entities.Autoanzeige;
import org.se2.ai.model.entities.Benutzer;
import org.se2.ai.model.entities.Kunde;
import org.se2.ai.model.factories.ReservierungsFactory;
import org.se2.services.util.Roles;

import java.time.LocalDate;

//author qthi2s


public class ReservierungsWindow extends Window {

    private static final String WIDTH_800_PX = "800px";
    private static final String HEIGHT_120_PX = "120px";
    private final transient Benutzer user = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);


    public ReservierungsWindow(Autoanzeige a) {
        center();
        VerticalLayout content = new VerticalLayout();
        Label titel = new Label("<b> Reservierung </b>", ContentMode.HTML);
        titel.setWidth(WIDTH_800_PX);

        content.addComponent(titel);
        content.setComponentAlignment(titel, Alignment.MIDDLE_CENTER);
        TextArea reservierungsstatus = new TextArea("Reservierungsstatus");
        reservierungsstatus.setWidth(WIDTH_800_PX);
        reservierungsstatus.setHeight(HEIGHT_120_PX);
        content.addComponent(reservierungsstatus);
        content.setComponentAlignment(reservierungsstatus, Alignment.MIDDLE_CENTER);


        HorizontalLayout button = new HorizontalLayout();
        button.setWidth(WIDTH_800_PX);

        Button zuruck = new Button("Zurück");
        zuruck.setWidth("150px");
        button.addComponent(zuruck);
        button.setComponentAlignment(zuruck, Alignment.MIDDLE_LEFT);
        zuruck.addClickListener(clickEvent -> close());

        Button abschicken = new Button("Abschicken");
        abschicken.addClickListener(clickEvent -> {
            Kunde k = KundeDAO.getInstance().getKunde(user.getEmail());
            ReservierungDTO reservieren = (ReservierungDTO) new ReservierungsFactory().create();
            reservieren.setKunde(k);
            reservieren.setAutoanzeige((AutoanzeigeDTO) a);
            reservieren.setStatus(reservierungsstatus.getValue());
            reservieren.setDatum(LocalDate.now());
            BestaetigungReservierung window = new BestaetigungReservierung((AutoanzeigeDTO) a, reservieren, k);
            UI.getCurrent().addWindow(window);
        });
        abschicken.setWidth("150px");
        button.addComponent(abschicken);
        button.setComponentAlignment(abschicken, Alignment.MIDDLE_RIGHT);

        content.addComponent(button);
        content.setComponentAlignment(button, Alignment.MIDDLE_CENTER);

        VerticalLayout main = new VerticalLayout();
        main.setWidth("1000px");

        main.addComponent(content);
        main.setComponentAlignment(content, Alignment.MIDDLE_CENTER);

        this.setContent(main);


    }

}
