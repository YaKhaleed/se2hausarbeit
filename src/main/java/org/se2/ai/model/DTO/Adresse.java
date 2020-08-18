package org.se2.ai.model.DTO;

public class Adresse {

    private String strasse;
    private String hausnummer;
    private int plz;
    private String ort;
    private int adresseID;

    public Adresse (){

    }

    public Adresse (String strasse, int plz, String hausnummer, String ort){
        this.strasse=strasse;
        this.hausnummer=hausnummer;
        this.plz=plz;
        this.ort=ort;
    }

    public void setPlz (int plz){
        this.plz=plz;
    }

    public int getPlz (){
        return plz;
    }

    public String getOrt(){
        return ort;
    }

    public void setOrt (String ort){
        this.ort=ort;
    }

    public void setStrasse (String strasse){
        this.strasse=strasse;
    }

    public String getStrasse(){
        return strasse;
    }

    public void setHausnummer (String hausnummer){
        this.hausnummer=hausnummer;
    }

    public String getHausnummer(){
        return hausnummer;
    }

    public void setAdresseID (int adresseID){
        this.adresseID=adresseID;
    }

    public int getAdresseID (){
        return adresseID;
    }

    public String toString (){
        return new StringBuilder().append(strasse).append(" ").append(hausnummer).append("\n").append(plz).append(ort).toString();
    }

}
