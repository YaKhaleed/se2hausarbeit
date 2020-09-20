package se2hausarbeit.ai.model.entities;

/**
 * @author qthi2s
 */


import org.junit.Before;
import org.junit.Test;
import org.se2.ai.model.entities.Autoanzeige;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AutoAnzeigeTest {

    private Autoanzeige autoanzeige;
    private final int autoanzeigenID = 1;
    private final String titel = "BMW x5";
    private final String beschreibung = "XXXXXXXXXXXXXXXXXXXX";
    private final String status = "Verfügbar";
    private LocalDate datum;
    private final int vertrieblerID = 10;
    private final String ort = "Bonn";



    @Before
    public void setup() {
        autoanzeige = new Autoanzeige();
        autoanzeige.setAutoanzeigenID(autoanzeigenID);
        autoanzeige.setTitel(titel);

        autoanzeige.setBeschreibung(beschreibung);
        autoanzeige.setStatus(status);
        autoanzeige.setVertrieblerID(vertrieblerID);

        autoanzeige.setOrt(ort);
        autoanzeige.setDatum(datum);

    }


    @Test
    public void testget() {
        assertEquals(autoanzeigenID, autoanzeige.getAutoanzeigenID());
        assertNotNull(autoanzeige.getAutoanzeigenID());
        assertEquals(titel, autoanzeige.getTitel());
        assertEquals(beschreibung, autoanzeige.getBeschreibung());
        assertEquals(status, autoanzeige.getStatus());
        assertEquals(ort, autoanzeige.getOrt());
        assertEquals(datum, autoanzeige.getDatum());


    }


}
