package org.se2.gui.components;

import org.se2.ai.control.exceptions.DatabaseException;

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

        Label ju = new Label(autoanzeige.getVertrieblerName() + " - ");
        ju.setWidth("600px");
        titel.addComponent(ju);


        VerticalLayout right = new VerticalLayout();

        right.addComponent(new Label(autoanzeige.getStatus()));

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


        return content;
    }
}

