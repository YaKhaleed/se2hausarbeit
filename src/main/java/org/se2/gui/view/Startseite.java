package org.se2.gui.view;

import org.se2.ai.model.entities.Benutzer;
import org.se2.gui.components.PanelStartseite;
import org.se2.gui.ui.MyUI;
import org.se2.services.util.Views;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author WINDOWS
 */
public class Startseite extends VerticalLayout implements View {
    public static final String CLASSNAME = "STARTSEITE";

    public void setUp() {

//GesamtgrÃ¶ÃŸe des Bildschirms auf komplette GrÃ¶ÃŸe beziehen
        this.setSizeFull();
        PanelStartseite panel = new PanelStartseite();
        panel.setHeight("50px");
        this.addComponent(panel);
        setMargin(true);
        GridLayout layout = new GridLayout(2, 2);
        layout.setSizeFull();

        //PROJEKT-NAME
        Label label = new Label("<b> CarLook </b>", ContentMode.HTML);
        label.setPrimaryStyleName(CLASSNAME + "-label");
        label.addStyleName("mytitle");
        label.addStyleName(ValoTheme.LABEL_H1);
        layout.addComponent(label, 0, 0);
        layout.setComponentAlignment(label, Alignment.TOP_CENTER);

        Label label2 = new Label("Wir von CarLook sind jetzt auch digital zu erreichen! <br>" +
                "Mit wenigen Klicks können Sie sich registrieren und <br> Autos für eine Besichtigung reservieren. <br> <br>"
                + "Viel Spaß beim Stöbern!<br>", ContentMode.HTML);

        label2.setPrimaryStyleName(CLASSNAME + "-label2");

        label2.setStyleName("startseite");
        layout.addComponent(label2, 1, 0);
        layout.setComponentAlignment(label2, Alignment.MIDDLE_CENTER);

        this.addComponent(layout);


    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();
        if (user != null) {
            UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);

        } else {
            this.setUp();
        }
    }
}
