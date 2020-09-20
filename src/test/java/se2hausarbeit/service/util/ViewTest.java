package se2hausarbeit.service.util;

/**
 * @author qthi2s
 */


import org.junit.Test;
import org.se2.services.util.Views;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ViewTest {

    private final String MAIN = "suche";
    private final String LOGIN = "login";

    @Test
    public void get() {
        assertEquals(MAIN, Views.MAIN);
        assertEquals(LOGIN, Views.LOGIN);
        assertNotNull(Views.DASHBOARDA);
    }
}
