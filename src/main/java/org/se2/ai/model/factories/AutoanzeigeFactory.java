package org.se2.ai.model.factories;

import org.se2.ai.model.entities.Vertriebler;
import org.se2.ai.model.entities.Autoanzeige;
import org.se2.ai.model.DTO.AutoanzeigeAnfrage;

/**
 * @author qthi2s
 */

public class AutoanzeigeFactory {
    private AutoanzeigeFactory (){

    }
    public static Autoanzeige createStellenanzeige(AutoanzeigeAnfrage anfrage, Vertriebler v) {
        Autoanzeige a = new Autoanzeige();

        a.setVertrieblerID(v.getVertrieblerID());
        a.setBeschreibung(anfrage.getBeschreibung());
        a.setDatum(anfrage.getDatum());
        a.setOrt(anfrage.getOrt());
        a.setTitel(anfrage.getTitel());
        a.setStatus(anfrage.getStatus());
        return a;
    }



}
