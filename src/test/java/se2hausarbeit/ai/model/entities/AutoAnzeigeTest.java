package se2hausarbeit.ai.model.entities;

import org.junit.Before;
import org.junit.Test;
import org.se2.ai.model.DTO.AnforderungAutoanzeige;
import org.se2.ai.model.entities.Autoanzeige;

import java.time.LocalDate;
import java.util.List;

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
    private List<AnforderungAutoanzeige> autoanforderung;


    @Before
    public void setup() {
        autoanzeige = new Autoanzeige();
        autoanzeige.setAutoanzeigenID(autoanzeigenID);
        autoanzeige.setTitel(titel);

        autoanzeige.setBeschreibung(beschreibung);
        autoanzeige.setStatus(status);
        autoanzeige.setVertrieblerID(vertrieblerID);

        autoanzeige.setOrt(ort);
        autoanzeige.setAutoanforderung(autoanforderung);
        autoanzeige.setDatum(datum);

    }


    @Test
    public void testget() {
        assertEquals(autoanzeigenID, autoanzeige.getAutoanzeigenID());
        assertNotNull(autoanzeige.getAutoanzeigenID());
        assertEquals(titel, autoanzeige.getTitel());
        assertEquals(beschreibung, autoanzeige.getBeschreibung());
        assertEquals(status, autoanzeige.getStatus());
        assertEquals(vertrieblerID, autoanzeige.getVertrieblerID());
        assertNotNull(autoanzeige.getVertrieblerID());
        assertEquals(ort, autoanzeige.getOrt());
        assertEquals(autoanforderung, autoanzeige.getAutoanforderung());
        assertEquals(datum, autoanzeige.getDatum());


    }


}
