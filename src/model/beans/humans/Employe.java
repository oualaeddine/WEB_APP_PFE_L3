package model.beans.humans;

import model.enums.UserType;

import java.util.Date;

public class Employe extends Person {
    private int creator;
    private boolean isSuspended;
    private boolean isApproved;
    private Date dateAdded;
    private UserType userType;

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Employe() {
        super();
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
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

    @Override
    public String toString() {
        return "Employe{" +
                "creator=" + creator +
                ", isSuspended=" + isSuspended +
                ", isApproved=" + isApproved +
                ", dateAdded=" + dateAdded +
                ", userType=" + userType +
                "} " + super.toString();
    }
}
