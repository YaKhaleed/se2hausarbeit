package org.se2.ai.control;

import org.se2.ai.model.DTO.AutoanzeigeDTO;

import java.util.List;

public class Suche implements SuchFunktion {

    private static Suche suche = null;

    private Suche() {
    }

    public static Suche getInstance() {
        if (suche == null) {
            suche = new Suche();
        }
        return suche;
    }

    @Override
    public List<AutoanzeigeDTO> getAutoanzeige(String titel, String ort) {
        return suche.getAutoanzeige(titel, ort);
    }
}
