package org.se2.ai.model.DTO;

import org.se2.ai.model.entities.Kunde;
import org.se2.ai.model.entities.Vertriebler;

import java.time.LocalDate;

import java.util.Objects;

/**
 * @author qthis
 */

public class ReservierungDTO implements BenoetigteDTO, Cloneable {


    private int id;
    private Kunde kunde;
    private AutoanzeigeDTO autoanzeige;
    private String status;
    private String reservieren;
    private Vertriebler vertriebler;
    private LocalDate datum;



    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        final ReservierungDTO other = (ReservierungDTO) o;
        if (this.autoanzeige != other.autoanzeige) {
            return false;
        }
        if (!Objects.equals(this.kunde, other.kunde)) {
            return false;
        }
        if (!Objects.equals(this.vertriebler, other.vertriebler)) {
            return false;
        }
        if (!Objects.equals(this.reservieren, other.reservieren)) {
            return false;


        }
        return Objects.equals(this.status, other.status);

    }

    @Override
    public String toString (){
        return  "Status:" + status +  "reservierungsID:" + autoanzeige.getAutoanzeigenID() + "," + "title:" + autoanzeige.getTitel() + ", ort:" + autoanzeige.getOrt();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public AutoanzeigeDTO getAutoanzeige() {
        return autoanzeige;
    }

    public void setAutoanzeige(AutoanzeigeDTO autoanzeige) {
        this.autoanzeige = autoanzeige;
    }

    public Vertriebler getVertriebler() {
        return vertriebler;
    }

    public void setVertriebler(Vertriebler vertriebler) {
        this.vertriebler = vertriebler;
    }

    public String getReservieren() {
        return reservieren;
    }

    public void setReservieren(String reservieren) {
        this.reservieren = reservieren;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }



}
