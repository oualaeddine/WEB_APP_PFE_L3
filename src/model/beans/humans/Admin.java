package model.beans.humans;

import model.enums.AdminRole;

import java.io.Serializable;
import java.util.Date;

public class Admin extends Person {
    private AdminRole role;
    private Date dateAdded;


    public Admin() {
        super();
    }

    public AdminRole getRole() {
        return role;
    }

    @Override
    public Date getDateAdded() {
        return dateAdded;
    }

    public void setRole(AdminRole role) {
        this.role = role;
    }

    public void setDateAdded(Date date) {
        this.dateAdded = date;
    }
}
