package org.se2.ai.model.DTO;

import org.se2.ai.model.entities.Vertriebler;

/**
 * @author qthis
 */

public class VertrieblerDTO extends Vertriebler {

    private int VertrieblerID;

    public void setVertrieblerID(int vertrieblerID) {

        this.VertrieblerID = vertrieblerID;
    }

    public int getVertrieblerID(){

        return VertrieblerID;
    }
}
