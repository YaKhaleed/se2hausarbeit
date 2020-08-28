package org.se2.ai.control;


import org.se2.ai.model.DTO.AutoanzeigeDTO;
import org.se2.ai.model.dao.AutoanzeigeDAO;
import org.se2.ai.model.entities.Autoanzeige;

public class AutoanzeigeControll {

    public void erstellen(Autoanzeige s) {
        AutoanzeigeDAO.getInstance().createAutoanzeige(s);
    }

    public AutoanzeigeDTO get(int stellenanzeigeid) {
        return AutoanzeigeDAO.getInstance().getJobangebot(stellenanzeigeid);
    }

    public boolean updatestatus(AutoanzeigeDTO s) {
        return AutoanzeigeDAO.getInstance().updateStatusAutoanzeige(s);
    }

    public boolean löscheautoanzeige(AutoanzeigeDTO s) {
        return AutoanzeigeDAO.getInstance().löscheautonanzeige(s);
    }
}
