package org.se2.ai.model.factories;

import org.se2.ai.model.DTO.BenötigteDTO;
import org.se2.ai.model.DTO.ReservierungDTO;
import org.se2.ai.model.entities.Kunde;
import org.se2.ai.model.entities.Vertriebler;

import java.util.ArrayList;
import java.util.List;

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
    public BenötigteDTO create() {
        return (BenötigteDTO) new ReservierungDTO();
    }

    public List<ReservierungDTO> getListBewerbungStudent(Kunde k) {
        liste = ReservierungsDAO.getInstance().getReservierungvonKunde(k);
        return liste;
    }

    public List<ReservierungDTO> getListBewerbungForArbeitgeber(Vertriebler v) {
        liste = ReservierungsDAO.getInstance().getReservierungvonVertriebler(v);
        return liste;
    }
}


