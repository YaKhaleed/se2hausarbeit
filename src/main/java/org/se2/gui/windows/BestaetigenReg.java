package org.se2.gui.windows;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;

/**
 * @author qthi2s
 */

public class BestaetigenReg extends Window {

    public BestaetigenReg (String text, String anzeige) {
        super("Erfolgreich!");
        center();

        //Some basic content for the window
        VerticalLayout content = new VerticalLayout();
        content.addComponent(new Label(text));
        content.setMargin(true);
        setContent(content);

        Button button = new Button("OK");
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        button.addClickListener(clickEvent -> {
            close();
            UI.getCurrent().getNavigator().navigateTo(anzeige);

        });
        content.addComponent(button);
        content.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }

}
