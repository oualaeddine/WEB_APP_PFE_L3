package model.beans.humans;

import model.enums.UserType;

import java.io.Serializable;
import java.util.Date;

public class Employe extends Person {
    private Employe creator;
    private boolean isSuspended;
    private Date dateAdded;
    private UserType userType;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Employe() {
        super();
    }

    public Employe getCreator() {
        return creator;
    }

    public void setCreator(Employe creator) {
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
