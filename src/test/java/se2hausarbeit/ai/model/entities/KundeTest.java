package se2hausarbeit.ai.model.entities;

/**
 * @author qthi2s
 */


import org.junit.Before;
import org.junit.Test;
import org.se2.ai.model.entities.Kunde;

// author qthi2s

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class KundeTest {

    private Kunde kunde;
    private final String nachname = "Thi";
    private final String vorname = "Tu";
    private final int kundenID = 3;



    @Before
    public void setup() {
        kunde = new Kunde();
        kunde.setVorname(vorname);
        kunde.setNachname(nachname);
        kunde.setKundenID(kundenID);


    }


    @Test
    public void testget() {
        assertEquals(kundenID, kunde.getKundenID());
        assertNotNull(kunde.getKundenID());
        assertEquals(vorname, kunde.getVorname());
        assertEquals(nachname, kunde.getNachname());


    }


}
