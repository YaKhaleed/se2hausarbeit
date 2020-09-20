package se2hausarbeit.ai.model.entities;

import org.junit.Before;
import org.junit.Test;
import org.se2.ai.model.dao.AdresseDAO;
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
    private AdresseDAO.Adresse adresse;

    @Before
    public void setup() {
        benutzer = new Benutzer();
        benutzer.setAdressID(adresseID);
        benutzer.setEmail(email);
        benutzer.setPasswort(passwort);
        //benutzer.setTelefonnummer(telefonnummer);
        benutzer.setId(Id);
        benutzer.setAnrede(anrede);
        benutzer.setRolle(rolle);
        benutzer.setAdresse(adresse);
    }

    @Test
    public void testget() {

        assertEquals(adresseID, benutzer.getAdressID());
        assertNotNull(benutzer.getAdressID());
        assertEquals(email, benutzer.getEmail());
        assertEquals(passwort, benutzer.getPasswort());
        assertEquals(Id, benutzer.getId());
        assertNotNull(benutzer.getId());
        assertEquals(anrede, benutzer.getAnrede());
        assertEquals(rolle, benutzer.getRolle());
        assertEquals(adresse, benutzer.getAdresse());


    }


}
