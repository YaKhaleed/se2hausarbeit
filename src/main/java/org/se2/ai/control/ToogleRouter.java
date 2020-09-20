package org.se2.ai.control;

import org.se2.ai.control.exceptions.DatabaseException;
import org.se2.ai.model.dao.ToogleFeatureDAO;

/**
 * @author qthi2s
 */

public class ToogleRouter {

    private ToogleRouter() {
    }

    public static boolean isEnabled(String feature) throws DatabaseException {
        return (ToogleFeatureDAO.getInstance().checkFeature(feature));
    }
}
