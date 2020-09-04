package se2hausarbeit.ai.controll;

import org.junit.Test;
import org.se2.ai.control.ToogleRouter;
import org.se2.ai.control.exceptions.DatabaseException;

public class FeatureToogleTest {

    @Test
    public void testFeatureToggle() throws DatabaseException {
        ToogleRouter.isEnabled("reservierung");
    }
}
