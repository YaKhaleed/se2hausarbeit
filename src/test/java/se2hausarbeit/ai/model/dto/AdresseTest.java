package se2hausarbeit.ai.model.dto;

import org.junit.Test;
import org.se2.ai.model.DTO.Adresse;

//author qthi2s

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdresseTest {

    @Test
    public void getPlz() {
        Adresse ad = new Adresse();
        ad.setPlz(53639);
       assertEquals(53639, ad.getPlz());
    }

    @Test
    public void setPlz() {
        Adresse ad = new Adresse();
        ad.setPlz(53639);
       assertEquals(53639, ad.getPlz());
    }

    @Test
    public void getOrt() {
        Adresse ad = new Adresse();
        ad.setOrt("bonn");
      assertEquals("bonn", ad.getOrt());
    }

    @Test
    public void setOrt() {
        Adresse ad = new Adresse();
        ad.setOrt("bonn");
        assertEquals("bonn", ad.getOrt());
    }

    @Test
    public void getStrasse() {
        Adresse ad = new Adresse();
        ad.setStrasse("HauptStr");
        assertEquals("HauptStr", ad.getStrasse());
    }

    @Test
    public void setStrasse() {
        Adresse ad = new Adresse();
        ad.setStrasse("HauptStr");
       assertEquals("HauptStr", ad.getStrasse());
    }



    @Test
    public void getHausnummer() {
        Adresse ad = new Adresse();
        ad.setHausnummer("1");
        assertEquals("1", ad.getHausnummer());
    }

    @Test
    public void setHausnummer() {
        Adresse ad = new Adresse();
        ad.setHausnummer("1");
        assertEquals("1", ad.getHausnummer());
    }

    @Test
    public void getAdresseID() {
        Adresse ad = new Adresse();
        ad.setAdresseID(1);
        assertEquals(1, ad.getAdresseID());
    }

    @Test
    public void setAdresseID() {
        Adresse ad = new Adresse();
        ad.setAdresseID(1);
        assertEquals(1, ad.getAdresseID());
    }

    @Test
    public void testToString() {
        Adresse ad = new Adresse();
        ad.setOrt("bonn");
        ad.setStrasse("HauptStr");
        ad.setPlz(53639);
        ad.setHausnummer("1");
        ad.setAdresseID(1);

        assertEquals("HauptStr 1\n53639bonn", ad.toString());

    }
}
