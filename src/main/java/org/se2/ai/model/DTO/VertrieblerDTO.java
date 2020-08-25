package org.se2.ai.model.DTO;

import org.se2.ai.model.entities.CarlookMA;


public class VertrieblerDTO extends CarlookMA {

    private int carlookID;

    public void setCarlookID(int carlookID) {

        this.carlookID = carlookID;
    }

    public int getCarlookID (){

        return carlookID;
    }
}
