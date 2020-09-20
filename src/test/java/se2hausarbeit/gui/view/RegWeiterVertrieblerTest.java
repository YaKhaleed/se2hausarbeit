package se2hausarbeit.gui.view;

/**
 * @author qthi2s
 */


import org.junit.Test;
import org.se2.gui.view.RegWeiterVertriebler;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;

public class RegWeiterVertrieblerTest {

    @Test
    public void setUp() {
        RegWeiterVertriebler ver = mock(RegWeiterVertriebler.class);
        assertDoesNotThrow(ver::setUp);
    }

    @Test
    public void enter() {
    }
}
