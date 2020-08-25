package org.se2.ai.model.DTO;

import org.se2.ai.model.entities.Vertriebler;


public class VertrieblerDTO extends Vertriebler {

    private int vertrieblerID;

    public void setVertrieblerID(int vertrieblerID) {
        this.vertrieblerID = vertrieblerID;
    }

    public int getVertrieblerID(){
        return vertrieblerID;
    }
}
