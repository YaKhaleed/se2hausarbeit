package se2hausarbeit.service.util;

/**
 * @author qthi2s
 */


import org.junit.Before;
import org.junit.Test;
import org.se2.services.util.PasswordAuthentication;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class PasswortTest {

    private final String strongP = "hallo1234";
    private PasswordAuthentication hashComputer;

    @Before
    public void setUp() {
        hashComputer = new PasswordAuthentication();
    }

    @Test
    public void testHash() {
        String hashedStrongPassword = hashComputer.hash(strongP.toCharArray());
        String sameHashedPassword = hashComputer.hash("hallo1234".toCharArray());
        assertNotEquals(hashedStrongPassword, sameHashedPassword);
        assertNotNull(hashedStrongPassword);
        assertNotNull(sameHashedPassword);
    }

    @Test
    public void testAuthenticate() {
        String somePasswordHash = hashComputer.hash(strongP.toCharArray());
        assertNotNull(hashComputer);

        assertTrue(hashComputer.authenticate("hallo1234".toCharArray(), somePasswordHash));

        assertFalse(hashComputer.authenticate("hallo1234 ".toCharArray(), somePasswordHash));
    }

}


