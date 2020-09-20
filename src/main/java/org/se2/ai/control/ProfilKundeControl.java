package org.se2.ai.control;

import com.vaadin.data.Binder;
import com.vaadin.server.VaadinSession;
import org.se2.ai.model.DTO.ReservierungDTO;
import org.se2.ai.model.dao.*;

import org.se2.ai.model.dao.AdresseDAO;
import org.se2.ai.model.entities.*;
import org.se2.services.util.Roles;

import java.util.List;

/**
 * @author qthi2s
 */

public class ProfilKundeControl {
        Binder<AdresseDAO.Adresse> adresseBinder = new Binder<>();
        Binder<Vertriebler> kundeBinder = new Binder<>();
        Benutzer benutzer = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);

        public List<ReservierungDTO> getReservierung(Kunde kunde) {
            return ReservierungDAO.getInstance().getReservierungFromKunde(kunde);
        }
    }

