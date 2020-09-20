package se2hausarbeit.ai.model.dto;

import org.junit.Before;
import org.junit.Test;
import org.se2.ai.model.DTO.AutoanzeigeDTO;
import org.se2.ai.model.entities.Vertriebler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class AutoanzeigedtoTest {

    AutoanzeigeDTO autoanzeigeDTO;
    private int autoanzeigeID;
    private String titel;
    private String beschreibung;
    private String status;
    private LocalDate datum;
    private int vertrieblerID;
    private String ort;
    private Vertriebler vertriebler;

    @Before
    public void setup() {
        autoanzeigeDTO = new AutoanzeigeDTO();
        autoanzeigeDTO.setOrt("Koenigswinter");
        autoanzeigeDTO.setTitel("Test");
        autoanzeigeDTO.setBeschreibung("Beschreibung");
        autoanzeigeDTO.setAutoanzeigenID(100);
        autoanzeigeDTO.setStatus("Offen");

    }

    @Test
    public void getStellenanzeige() {

        assertEquals(autoanzeigeDTO.getAutoanzeigenID(), 100);
        assertEquals(autoanzeigeDTO.getOrt(), "koenigswinter");
        assertEquals("Test", autoanzeigeDTO.getTitel());
        assertEquals("Offen", autoanzeigeDTO.getStatus());
        assertEquals("Beschreibung", autoanzeigeDTO.getBeschreibung());
        assertEquals("Test", autoanzeigeDTO.getVertriebler());
    }
}
