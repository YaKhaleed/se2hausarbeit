package org.se2.ai.model.entities;

/**
 * @author qthi2s
 */

public class Vertriebler extends Benutzer {

    private byte profilbild;
    private int vertrieblerID;
    private String name;
    private String vorname;


    public byte getProfilbild() {
        return profilbild;
    }

    public void setProfilbild(byte profilbild) {
        this.profilbild = profilbild;
    }

    public int getVertrieblerID() {
        return vertrieblerID;
    }

    public void setVertrieblerID(int vertrieblerID) {
        this.vertrieblerID = vertrieblerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }


}
