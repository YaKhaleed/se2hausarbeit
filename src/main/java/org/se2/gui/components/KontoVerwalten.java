package org.se2.gui.components;

import org.se2.ai.control.KontoControl;
import org.se2.ai.model.entities.Benutzer;
import org.se2.gui.windows.BestaetigungWindows;
import org.se2.gui.windows.KontoLoeschenWindow;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;


public class KontoVerwalten extends VerticalLayout {
    static final String PX_300 = "300px";

    public KontoVerwalten(Benutzer user) {
        KontoControl kc = new KontoControl();

        VerticalLayout top = new VerticalLayout();
        top.setWidth("1000px");
        top.setHeight("150px");
        Label label = new Label("<b> Konto verwalten </b>", ContentMode.HTML);
        label.addStyleName("mytitle");
        top.addComponent(label);

        Label label2 = new Label("Sie kÃ¶nnen hier Ihr Konto lÃ¶schen oder Ihr Passwort Ã¤ndern.");
        top.addComponent(label2);

        this.addComponent(top);
        this.setComponentAlignment(top, Alignment.TOP_CENTER);

        HorizontalLayout main = new HorizontalLayout();
        main.setWidth("1000px");
        Label loschen = new Label("Konto lÃ¶schen");

        VerticalLayout deletekonto = new VerticalLayout();

        deletekonto.addComponent(loschen);

        TextField mail = new TextField();
        mail.setPlaceholder("E-Mail Adresse");
        mail.setWidth(PX_300);
        deletekonto.addComponent(mail);

        PasswordField passwort = new PasswordField();
        passwort.setPlaceholder("Passwort");
        passwort.setWidth(PX_300);
        deletekonto.addComponent(passwort);


        Button delete = new Button("LÃ¶schen");
        delete.addClickListener(clickEvent -> {
            KontoLoeschenWindow deleteWindow = new KontoLoeschenWindow(mail.getValue(), passwort.getValue());
            UI.getCurrent().addWindow(deleteWindow);
        });
        deletekonto.addComponent(delete);

        VerticalLayout changepassword = new VerticalLayout();
        Label change = new Label("Passwort Ã¤ndern");

        changepassword.addComponent(change);

        final TextField mail2 = new TextField();
        mail2.setPlaceholder("E-Mail Adresse");
        mail2.setValue(user.getEmail());
        mail2.setWidth(PX_300);
        changepassword.addComponent(mail2);

        final PasswordField alt = new PasswordField();
        alt.setPlaceholder("Altes Passwort");
        alt.setWidth(PX_300);
        changepassword.addComponent(alt);

        final PasswordField neu = new PasswordField();
        neu.setPlaceholder("Neues Passwort");
        neu.setWidth(PX_300);
        changepassword.addComponent(neu);

        final Button andern = new Button("Ã„ndern");
        changepassword.addComponent(andern);
        andern.addClickListener(clickEvent -> {
            String email = mail2.getValue();
            String altpasswort = alt.getValue();
            String neupasswort = neu.getValue();
            boolean check = kc.updateKonto(email, altpasswort, neupasswort);
            if (check) {
                BestaetigungWindows confirm = new BestaetigungWindows("Erfolgreich");
                UI.getCurrent().addWindow(confirm);
            }
        });

        main.addComponent(deletekonto);
        main.setComponentAlignment(deletekonto, Alignment.TOP_LEFT);
        main.addComponent(changepassword);
        main.setComponentAlignment(changepassword, Alignment.TOP_RIGHT);
        this.addComponent(main);
        this.setComponentAlignment(main, Alignment.TOP_CENTER);


    }

}
