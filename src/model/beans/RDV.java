package model.beans;

import java.sql.Date;

public class RDV {
    private Date date;
    private int horraire;

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHorraire(int horraire) {
        this.horraire = horraire;
    }

    public int getHorraire() {
        return horraire;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof RDV)) return false;

        RDV rdv = (RDV) object;

        return horraire == rdv.horraire && (date != null ? date.equals(rdv.date) : rdv.date == null);
    }

    @Override
    public String toString() {
        return "RDV{" +
                "date=" + date +
                ", horraire=" + horraire +
                '}';
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + horraire;
        return result;
    }
}
