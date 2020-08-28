package org.se2.ai.model.factories;

import org.se2.ai.model.entities.Vertriebler;
import org.se2.ai.model.entities.Autoanzeige;
import org.se2.ai.model.DTO.AutoanzeigeAnfrage;



public class AutoanzeigeFactory {
    private AutoanzeigeFactory (){

    }
    public static Autoanzeige createStellenanzeige(AutoanzeigeAnfrage Anfrage, Vertriebler v) {
        Autoanzeige a = new Autoanzeige();

        a.setVertrieberID(v.getVertrieberID());
        a.setBeschreibung(Anfrage.getBeschreibung());
        a.setDatum(Anfrage.getDatum());
        a.setOrt(Anfrage.getOrt());
        a.setTitel(Anfrage.getTitel());
        a.setStatus(Anfrage.getStatus());
        return a;
    }



}
