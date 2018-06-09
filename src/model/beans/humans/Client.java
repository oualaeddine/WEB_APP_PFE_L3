package model.beans.humans;

import java.sql.Date;

public class Client extends Person {
    private boolean isBanned;
    private Date dateAdded;

    public Client() {
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public String isBannedString() {
        if (isBanned)
            return "banned";
        else
            return "not banned";
    }

    @Override
    public String toString() {
        return "Client{" +
                "isBanned=" + isBanned +
                "} " + super.toString();
    }
}
