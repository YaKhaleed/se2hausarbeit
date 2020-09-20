package org.se2.ai.control;

import org.se2.ai.model.dao.ReservierungDAO;
import org.se2.ai.model.DTO.ReservierungDTO;
import org.se2.ai.model.DTO.AutoanzeigeDTO;
import org.se2.ai.model.entities.Kunde;

/**
 * @author qthi2s
 */

public class ReservierungControl {
    public void createReservierung(AutoanzeigeDTO a, ReservierungDTO r, Kunde k) {
        ReservierungDAO.getInstance().createReservierung(a, r, k);
    }

    /**
     * Update the status of a job application
     *
     * @param reservierung which job application
     */
    public void updatestatus(ReservierungDTO reservierung) {
        ReservierungDAO.getInstance().updateStatusBewerbung(reservierung);
    }

}
