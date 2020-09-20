package se2hausarbeit.gui.view;

/**
 * @author qthi2s
 */


import org.junit.Test;
import org.se2.gui.view.RegWeiterKunde;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;

public class RegweiterKundeTest {


    @Test
    public void setUp() {
        RegWeiterKunde ku = mock(RegWeiterKunde.class);
        assertDoesNotThrow(ku::setUp);
    }

    @Test
    public void enter() {
    }


}


