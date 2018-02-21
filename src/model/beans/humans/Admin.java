package model.beans.humans;

import model.enums.AdminRole;

import java.io.Serializable;
import java.util.Date;

public class Admin extends Person {
    private AdminRole role;


    public Admin() {
        super();
    }

    public AdminRole getRole() {
        return role;
    }

    public void setRole(AdminRole role) {
        this.role = role;
    }

}
