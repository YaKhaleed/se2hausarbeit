package org.se2.ai.model.entities;

import org.se2.ai.model.DTO.Adresse;

public class Benutzer {

    private int adressID;
    private String email;
    private String passwort;
    private String telefonnummer = null;
    private int Id;
    private String anrede;
    private Adresse adresse;
    private String rolle;

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

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

    //Benutzerid = email
    public int getId(){
        return Id;
    }

    public void setId(int userID) {
        this.Id = userID;
    }



}
