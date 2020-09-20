package se2hausarbeit.ai.model.dto;

import org.junit.Test;
import org.se2.ai.model.DTO.RolleDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author qthi2s
 */


public class RollenDtoTest {

    @Test
    public void getBezeichnung() {
        RolleDTO r = new RolleDTO();
        r.setBezeichnung("bez");
        assertEquals("bez", r.getBezeichnung());

    }

    @Test
    public void setBezeichnung() {
        RolleDTO r = new RolleDTO();
        r.setBezeichnung("bez");
        assertEquals("bez", r.getBezeichnung());
    }
}
