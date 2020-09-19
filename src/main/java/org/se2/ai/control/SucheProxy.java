package org.se2.ai.control;

import org.se2.ai.model.DTO.AutoanzeigeDTO;

import java.util.List;

public class SucheProxy implements SuchFunktion{
    private static SucheProxy suche = null;

    private SucheProxy() {
    }

    public static SucheProxy getInstance() {
        if (suche == null) {
            suche = SucheProxy.getInstance();
        }
        return suche;
    }




    @Override
    public List<AutoanzeigeDTO> getAutoanzeigeListe(String titel) {
        return suche.getAutoanzeigeListe(titel);
    }
}
