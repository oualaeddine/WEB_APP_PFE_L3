package model.beans.humans;

import java.io.Serializable;
import java.util.Date;

public class Employe extends Person {
    private Admin creator;
    private boolean isSuspended;
    private Date dateAdded;

    Employe() {
        super();
    }

    public Admin getCreator() {
        return creator;
    }

    public void setCreator(Admin creator) {
        this.creator = creator;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }


    public String isSuspendedString() {
        if (isSuspended) return "suspended";
        return "not Suspended";
    }
}
