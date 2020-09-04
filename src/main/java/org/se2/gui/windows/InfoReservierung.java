package org.se2.gui.windows;

import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.se2.ai.model.DTO.ReservierungDTO;

//author qthi2s

public class InfoReservierung extends Window {

    private static final String WIDTH_950_PX = "950px";

    public InfoReservierung(ReservierungDTO r) {
        center();
        VerticalLayout content = new VerticalLayout();
        Label titel = new Label("Information zur Reservierung");

        TextArea rg = new TextArea("Status");
        rg.setReadOnly(true);
        rg.setWidth(WIDTH_950_PX);
        rg.setValue(r.getStatus());

        content.addComponent(rg);


        this.setContent(content);

    }



}
