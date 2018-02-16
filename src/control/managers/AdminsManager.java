package control.managers;

import model.beans.humans.Admin;
import model.enums.UserType;

public class AdminsManager {
    private final Admin loggedInAdmin;

    public AdminsManager(Admin loggedInAdmin) {
        this.loggedInAdmin = loggedInAdmin;
    }

    public boolean suspendEmploye(UserType userType, int userId) {
        return false;
    }
}
