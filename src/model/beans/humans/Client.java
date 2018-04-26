package model.beans.humans;

import java.io.Serializable;
import java.util.Date;

public class Client extends Person {
    private boolean isBanned;


    public Client() {
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


}
