package org.se2.gui.windows;

import com.vaadin.ui.*;

/**
 * @author qthi2s
 */

public class BestaetigungWindows extends Window {

    public BestaetigungWindows(String text) {
        super("Bestaetigen"); //Set Window caption
        center();

        //Some basic content for the window
        VerticalLayout content = new VerticalLayout();
        content.addComponent(new Label(text));
        content.setMargin(true);
        setContent(content);

        Button reservierungButton = new Button("OK");
        reservierungButton.addClickListener(newevent -> close());
        content.addComponent(reservierungButton);
        content.setComponentAlignment(reservierungButton, Alignment.MIDDLE_CENTER);
    }


}
