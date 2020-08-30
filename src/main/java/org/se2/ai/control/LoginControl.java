package org.se2.ai.control;

import com.vaadin.data.Binder;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.control.exceptions.NoSuchUserOrPassword;
import org.se2.ai.model.DTO.Adresse;
import org.se2.ai.model.DTO.AutoanzeigeDTO;
import org.se2.ai.model.DTO.VertrieblerDTO;
import org.se2.ai.model.dao.AdresseDAO;
import org.se2.ai.model.dao.AutoanzeigeDAO;
import org.se2.ai.model.dao.BenutzerDAO;
import org.se2.ai.model.dao.VertrieblerDAO;
import org.se2.ai.model.entities.Benutzer;
import org.se2.ai.model.entities.Vertriebler;
import org.se2.gui.ui.MyUI;
import org.se2.services.util.Roles;
import org.se2.services.util.Views;

import java.util.List;

public class LoginControl {

    private LoginControl(){

    }

    public static void authentification (String email, String password) throws NoSuchUserOrPassword, DatabaseException {

        Benutzer benutzer = BenutzerDAO.getBenutzer (email, password);

        ((MyUI) UI.getCurrent()).setBenutzer(benutzer);

        UI.getCurrent().getSession().setAttribute(Roles.CURRENTUSER, benutzer);

        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);


    }

    public static void logoutbenutzer() {
        UI.getCurrent().close();
        UI.getCurrent().getSession().close();
        UI.getCurrent().getPage().setLocation(Views.LOGIN);
    }

    public static class ProfilVertrielblerControll {

        Binder<Adresse> adresseBinder = new Binder<>();
        Binder<Vertriebler> vertrieblerBinder = new Binder<>();
        Benutzer benutzer = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);

        public List<AutoanzeigeDTO> getAutoanzeige(String vertriebler) {
            return AutoanzeigeDAO.getInstance().getAutoanzeigeByVertriebler(vertriebler);
        }

        public boolean updateVertrieblerprofil(VertrieblerDTO v) {
            boolean ergebnis = VertrieblerDAO.getInstance().updateVertriebler(v);
            boolean ergebnis2 = AdresseDAO.getInstance().updateAdresse(v.getAdresse());
            boolean ergebnis3 = BenutzerDAO.getInstance().updateStammdaten(v, benutzer.getAnrede(), benutzer);
            return ergebnis && ergebnis2 && ergebnis3;
        }
    }
}
