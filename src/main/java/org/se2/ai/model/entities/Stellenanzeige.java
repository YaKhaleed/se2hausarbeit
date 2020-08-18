package org.se2.ai.model.entities;

import java.time.LocalDate;

public class Stellenanzeige {

    private int stellenanzeigenID;
    private String titel;
    private String beschreibung;
    private int baujahr;
    private String status;
    private LocalDate datum;
    private String ort;

    public void setStellenanzeigenID(int stellenanzeigenID) {
        this.stellenanzeigenID = stellenanzeigenID;
    }

    public int getStellenanzeigenID() {
        return stellenanzeigenID;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getTitel() {
        return titel;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBaujahr(int baujahr) {
        this.baujahr = baujahr;
    }

    public int getBaujahr() {
        return baujahr;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
}
