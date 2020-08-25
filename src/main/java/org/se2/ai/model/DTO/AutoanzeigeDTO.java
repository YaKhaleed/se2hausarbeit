package org.se2.ai.model.DTO;

import org.se2.ai.model.entities.Autoanzeige;
import org.se2.ai.model.entities.CarlookMA;

public class AutoanzeigeDTO extends Autoanzeige {
    private String arbeitgeber;
    private String ort;
    private CarlookMA mitarbeiter;

    public CarlookMA getMitarbeiter(){
        return mitarbeiter;
    }

    public void setMitarbeiter (CarlookMA mitarbeiter){
        this.mitarbeiter=mitarbeiter;
    }

   /* public String getCarlookMA (){
        return carlookMA;
    }

    public void setCarlookMA (String carlookMA){
        this.carlookMA=carlookMA;
    } */
    @Override
    public String getOrt (){
        return ort;
    }
    @Override
    public void setOrt (String ort){
        this.ort=ort;
    }

}
