package org.se2.ai.model.entities;

public class Vertriebler extends Benutzer {

    private byte profilbild;
    private int vertrieberID;


    public byte getProfilbild() {
        return profilbild;
    }

    public void setProfilbild(byte profilbild) {
        this.profilbild = profilbild;
    }

    public int getVertrieberID() {
        return vertrieberID;
    }

    public void setVertrieberID(int vertrieberID) {
        this.vertrieberID = vertrieberID;
    }


}
