package org.se2.ai.control;

import com.vaadin.data.Binder;
import com.vaadin.server.VaadinSession;
import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.model.DTO.Adresse;
import org.se2.ai.model.DTO.AutoanzeigeDTO;
import org.se2.ai.model.DTO.ReservierungDTO;
import org.se2.ai.model.dao.*;

import org.se2.ai.model.entities.*;
import org.se2.gui.components.ProfilKundeVerwalten;
import org.se2.services.util.Roles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

    public class ProfilKundeControl {
        Binder<Adresse> adresseBinder = new Binder<>();
        Binder<Vertriebler> kundeBinder = new Binder<>();
        Benutzer benutzer = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);

        public List<ReservierungDTO> getReservierung(Kunde kunde) {
            return ReservierungDAO.getInstance().getReservierungFromKunde(kunde);
        }
    }

