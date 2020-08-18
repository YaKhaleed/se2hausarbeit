package org.se2.ai.model.entities;

import org.se2.ai.model.DTO.Adresse;

public class User {

    private int adressID;
    private String email;
    private String passwort;
    private String telefonnummer = null;
    private int UserID;
    private String anrede;
    private Adresse adresse;

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse (Adresse adresse){
        this.adresse=adresse;
    }

    public int getAdressID(){
    return adressID;
    }

    public void setAdressID(int adressID){
        this.adressID= adressID;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswort(){
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
    public String getAnrede(){
        return anrede;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }
    public String getTelefonnummer(){
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }
    public int getUserID(){
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }
}
