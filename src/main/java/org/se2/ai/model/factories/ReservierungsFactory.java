package org.se2.ai.model.factories;

import org.se2.ai.model.DTO.BenoetigteDTO;
import org.se2.ai.model.DTO.ReservierungDTO;
import org.se2.ai.model.dao.ReservierungDAO;
import org.se2.ai.model.entities.Kunde;
import org.se2.ai.model.entities.Vertriebler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qthi2s
 */

public class ReservierungsFactory implements BenötigeFactory{
    private static ReservierungDTO instance = null;
    private List<ReservierungDTO> liste = new ArrayList<>();

    public static ReservierungDTO getInstance(){
        if (instance==null){
            instance = new ReservierungDTO ();
        }
        return instance;
    }

    @Override
    public BenoetigteDTO create() {
        return  new ReservierungDTO();
    }

    public List<ReservierungDTO> getListReservierungKunde(Kunde k) {
        liste = ReservierungDAO.getInstance().getReservierungFromKunde(k);
        return liste;
    }

    /*
    public List<ReservierungDTO> getListBewerbungForArbeitgeber(Vertriebler v) {
        liste = ReservierungDAO.getInstance().getReservierungFromKunde(v);
        return liste;
    }

     */
}


