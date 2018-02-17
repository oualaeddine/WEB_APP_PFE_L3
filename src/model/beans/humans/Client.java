package model.beans.humans;

import java.io.Serializable;
import java.util.Date;

public class Client extends Person  {
    private boolean isBanned;
    private Date dateAdded;


    public Client() {
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }


}
