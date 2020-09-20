package org.se2.ai.control;

import com.vaadin.data.Binder;
import com.vaadin.server.VaadinSession;
import org.se2.ai.model.DTO.AutoanzeigeDTO;
import org.se2.ai.model.DTO.VertrieblerDTO;
import org.se2.ai.model.dao.AutoanzeigeDAO;
import org.se2.ai.model.dao.BenutzerDAO;
import org.se2.ai.model.dao.VertrieblerDAO;
import org.se2.ai.model.entities.Benutzer;
import org.se2.ai.model.entities.Vertriebler;
import org.se2.services.util.Roles;

import java.util.List;

/**
 * @author qthi2s
 */

public class ProfilVertrieblerControl {


    Binder<Vertriebler> vertrieblerBinder = new Binder<>();
    Benutzer benutzer = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);

    public List<AutoanzeigeDTO> getAutoanzeige(String vertriebler) {
        return AutoanzeigeDAO.getInstance().getAutoanzeigeByVertriebler(vertriebler);
    }

    public boolean updateVertrieblerprofil(VertrieblerDTO v) {
        boolean ergebnis = VertrieblerDAO.getInstance().updateVertriebler(v);
        boolean ergebnis2 = BenutzerDAO.getInstance().updateStammdaten(v, benutzer.getAnrede(), benutzer);
        return ergebnis && ergebnis2 ;
    }


}
