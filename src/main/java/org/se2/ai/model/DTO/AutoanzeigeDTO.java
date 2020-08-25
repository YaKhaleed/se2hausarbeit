package org.se2.ai.model.DTO;

import org.se2.ai.model.entities.Autoanzeige;

public class AutoanzeigeDTO extends Autoanzeige {
    private String Vertriebler;
    private String ort;
    private org.se2.ai.model.entities.Vertriebler v;

    public org.se2.ai.model.entities.Vertriebler getV() {
        return v;
    }

    public void setV (org.se2.ai.model.entities.Vertriebler v) {
        this.v = v;
    }


    public String getVertriebler() {
        return Vertriebler;
    }

    public void setVertriebler(String Vertriebler) {
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
