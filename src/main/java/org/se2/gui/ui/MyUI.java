package org.se2.gui.ui;

import javax.servlet.annotation.WebServlet;



import org.se2.ai.model.entities.Benutzer;
import org.se2.gui.view.*;
import org.se2.services.util.Roles;
import org.se2.services.util.Views;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author qthi2s
 */

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Title("CarLook")
@PreserveOnRefresh
public class MyUI extends UI {


    private transient Benutzer benutzer = null;

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VaadinSession sess = VaadinSession.getCurrent();
        String mySession = sess.toString();
        Logger.getLogger(MyUI.class.getName()).log(Level.INFO, mySession);
        Navigator navi = new Navigator(this, this);
        navi.addView(Views.MAIN, SucheSeite.class);
        navi.addView(Views.LOGIN, LoginSeite.class);
        navi.addView(Views.REGISTER, RegisterSeite.class);
        navi.addView(Views.PASSWORTVERGESSEN, Passwortvergessenseite.class);
        navi.addView(Views.STARTSEITE, Startseite.class);
        navi.addView(Views.REGISTERFUERKUNDE, RegisterseitefuerKunde.class);
        navi.addView(Views.REGISTERFUERVERTRIEBLER, RegisterseitefuerVertriebler.class);
        navi.addView(Views.REGWEITERS, RegWeiterKunde.class);
        navi.addView(Views.REGWEITERA, RegWeiterVertriebler.class);
        navi.addView(Views.DASHBOARDS, DashboardKunde.class);
        navi.addView(Views.DASHBOARDA, DashboardVertriebler.class);
        navi.addView(Views.AUTOANZEIGEERSTELLEN, AutoanzeigeErstellen.class);

        this.benutzer = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);
        UI.getCurrent().getNavigator().navigateTo(Views.STARTSEITE);

    }

    public MyUI getMyUI() {
        return (MyUI) UI.getCurrent();
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}

