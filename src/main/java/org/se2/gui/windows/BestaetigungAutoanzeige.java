package org.se2.gui.windows;

import com.vaadin.ui.*;
import org.se2.ai.model.entities.Autoanzeige;
import org.se2.ai.control.AutoanzeigeControl;

/**
 * @author qthi2s
 */

public class BestaetigungAutoanzeige extends Window {

    public BestaetigungAutoanzeige(Autoanzeige a) {
        center();

        //Some basic content for the window
        VerticalLayout content = new VerticalLayout();
        content.addComponent(new Label("Sind Sie sicher, dass Sie die Autoanzeige \t" +
                "jetzt hochladen möchten?"));
        content.setMargin(true);
        setContent(content);

        HorizontalLayout janein = new HorizontalLayout();
        janein.setWidth("550px");

        Button nein = new Button("Nein");
        janein.addComponent(nein);
        nein.addClickListener(clickEvent -> close());
        janein.setComponentAlignment(nein, Alignment.MIDDLE_LEFT);

        Button ja = new Button("Ja");
        janein.addComponent(ja);
        ja.addClickListener(clickEvent -> {
            AutoanzeigeControl c = new AutoanzeigeControl();
            c.erstellen(a);
            Window confirm = new Window("Super! Die Autoanzeige ist jetzt Online");
            confirm.center();
            confirm.setWidth("550px");
            UI.getCurrent().addWindow(confirm);
            close();
        });
        janein.setComponentAlignment(ja, Alignment.MIDDLE_RIGHT);

        content.addComponent(janein);
        content.setComponentAlignment(janein, Alignment.BOTTOM_CENTER);
    }





}
