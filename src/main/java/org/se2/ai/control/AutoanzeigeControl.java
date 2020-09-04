package org.se2.ai.control;


import org.se2.ai.model.DTO.AutoanzeigeDTO;
import org.se2.ai.model.dao.AutoanzeigeDAO;
import org.se2.ai.model.entities.Autoanzeige;

public class AutoanzeigeControl {

    public void erstellen(Autoanzeige s) {
        AutoanzeigeDAO.getInstance().createAutoanzeige(s);
    }

    public AutoanzeigeDTO get(int autoanzeigeid) {
        return AutoanzeigeDAO.getInstance().getAutoanzeigeByID(autoanzeigeid);
    }

    public boolean updatestatus(AutoanzeigeDTO s) {
        return AutoanzeigeDAO.getInstance().updateStatusAutoanzeige(s);
    }

    public boolean loescheautoanzeige(AutoanzeigeDTO s) {
        return AutoanzeigeDAO.getInstance().deleteAutoanzeige(s);
    }
}
