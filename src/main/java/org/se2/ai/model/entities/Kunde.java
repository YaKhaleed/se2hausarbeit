package org.se2.ai.model.entities;


public class Kunde extends User{

    private String nachname;
    private String vorname;
    private int kundenID;
    private byte profilbild;

    public void setKundenID(int kundenID) {
        this.kundenID = kundenID;
    }

    public int getKundenID() {
        return kundenID;
    }

    public void setProfilbild(byte profilbild) {
        this.profilbild = profilbild;
    }

    public byte getProfilbild() {
        return profilbild;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getVorname() {
        return vorname;
    }
}
