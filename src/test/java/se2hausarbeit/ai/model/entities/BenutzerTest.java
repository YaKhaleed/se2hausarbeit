package se2hausarbeit.ai.model.entities;

import org.junit.Before;
import org.junit.Test;
import org.se2.ai.model.entities.Benutzer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BenutzerTest {

    private Benutzer benutzer;

    private final int adresseID = 10;
    private final String email = "hanspeter@gmail.com";
    private final String passwort = "hallo12345";
    private final int Id = 1;
    private final String anrede = "Herr";
    private final String rolle = "Kunde";

    @Before
    public void setup() {
        benutzer = new Benutzer();
        benutzer.setEmail(email);
        benutzer.setPasswort(passwort);
        benutzer.setId(Id);
        benutzer.setAnrede(anrede);
        benutzer.setRolle(rolle);
    }

    @Test
    public void testget() {

        assertEquals(email, benutzer.getEmail());
        assertEquals(passwort, benutzer.getPasswort());
        assertEquals(Id, benutzer.getId());
        assertNotNull(benutzer.getId());
        assertEquals(anrede, benutzer.getAnrede());
        assertEquals(rolle, benutzer.getRolle());


    }


}
