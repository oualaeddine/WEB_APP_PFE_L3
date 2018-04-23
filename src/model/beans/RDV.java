package model.beans;

import java.sql.Date;

public class RDV {
    private Date date;
    private int horraire;

    public RDV() {
    }

    @Override
    public String toString() {
        return "RDV{" +
                "date=" + date +
                ", horraire=" + horraire +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHorraire() {
        return horraire;
    }

    public void setHorraire(int horraire) {
        this.horraire = horraire;
    }
}
