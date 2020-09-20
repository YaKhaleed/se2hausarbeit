package org.se2.ai.model.DTO;

import org.se2.ai.model.entities.Autoanzeige;
import org.se2.ai.model.entities.Vertriebler;

/**
 * @author qthis
 */

public class AutoanzeigeDTO extends Autoanzeige {

    private String Vertriebler;
    private String ort;
    private Vertriebler vertriebler;

    public Vertriebler getVertriebler() {

        return vertriebler;
    }

    public void setVertriebler(Vertriebler vertriebler) {

        this.vertriebler = vertriebler;
    }


    public String getVertrieblerName() {

        return Vertriebler;
    }
//nochmal drübergehen
    public void setVertrieblerName(String Vertriebler) {
        this.Vertriebler = Vertriebler;
    }

    @Override
    public String getOrt() {
        return ort;
    }

    @Override
    public void setOrt(String ort) {
        this.ort = ort;
    }

}
