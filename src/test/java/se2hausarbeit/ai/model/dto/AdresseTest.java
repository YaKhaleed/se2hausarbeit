package se2hausarbeit.ai.model.dto;

import org.junit.Test;
import org.se2.ai.model.dao.AdresseDAO;

//author qthi2s

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdresseTest {

    @Test
    public void getPlz() {
        AdresseDAO.Adresse ad = new AdresseDAO.Adresse();
        ad.setPlz(53639);
       assertEquals(53639, ad.getPlz());
    }

    @Test
    public void setPlz() {
        AdresseDAO.Adresse ad = new AdresseDAO.Adresse();
        ad.setPlz(53639);
       assertEquals(53639, ad.getPlz());
    }

    @Test
    public void getOrt() {
        AdresseDAO.Adresse ad = new AdresseDAO.Adresse();
        ad.setOrt("bonn");
      assertEquals("bonn", ad.getOrt());
    }

    @Test
    public void setOrt() {
        AdresseDAO.Adresse ad = new AdresseDAO.Adresse();
        ad.setOrt("bonn");
        assertEquals("bonn", ad.getOrt());
    }

    @Test
    public void getStrasse() {
        AdresseDAO.Adresse ad = new AdresseDAO.Adresse();
        ad.setStrasse("HauptStr");
        assertEquals("HauptStr", ad.getStrasse());
    }

    @Test
    public void setStrasse() {
        AdresseDAO.Adresse ad = new AdresseDAO.Adresse();
        ad.setStrasse("HauptStr");
       assertEquals("HauptStr", ad.getStrasse());
    }



    @Test
    public void getHausnummer() {
        AdresseDAO.Adresse ad = new AdresseDAO.Adresse();
        ad.setHausnummer("1");
        assertEquals("1", ad.getHausnummer());
    }

    @Test
    public void setHausnummer() {
        AdresseDAO.Adresse ad = new AdresseDAO.Adresse();
        ad.setHausnummer("1");
        assertEquals("1", ad.getHausnummer());
    }

    @Test
    public void getAdresseID() {
        AdresseDAO.Adresse ad = new AdresseDAO.Adresse();
        ad.setAdresseID(1);
        assertEquals(1, ad.getAdresseID());
    }

    @Test
    public void setAdresseID() {
        AdresseDAO.Adresse ad = new AdresseDAO.Adresse();
        ad.setAdresseID(1);
        assertEquals(1, ad.getAdresseID());
    }

    @Test
    public void testToString() {
        AdresseDAO.Adresse ad = new AdresseDAO.Adresse();
        ad.setOrt("bonn");
        ad.setStrasse("HauptStr");
        ad.setPlz(53639);
        ad.setHausnummer("1");
        ad.setAdresseID(1);

        assertEquals("HauptStr 1\n53639bonn", ad.toString());

    }
}
