package se2hausarbeit.ai.model.entities;

import org.junit.Before;
import org.junit.Test;
import org.se2.ai.model.entities.Vertriebler;

// author qthi2s


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VertrieblerTest {

    private Vertriebler vertriebler;
    private final String name = "=Müller";
    private final String vorname = "Hans";
    private final int vertrieblerID = 2;
    private byte profilbild;


    @Before
    public void setup() {
        vertriebler = new Vertriebler();
        vertriebler.setName(name);
        vertriebler.setVorname(vorname);
        vertriebler.setVertrieblerID(vertrieblerID);
        vertriebler.setProfilbild(profilbild);

    }


    @Test
    public void testget() {
        assertEquals(vertrieblerID, vertriebler.getVertrieblerID());
        assertNotNull(vertriebler.getVertrieblerID());
        assertEquals(name, vertriebler.getName());
        assertEquals(vorname, vertriebler.getVorname());
        assertEquals(profilbild, vertriebler.getProfilbild());

    }


}
