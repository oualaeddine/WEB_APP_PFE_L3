package control.system.managers;

import model.beans.humans.Employe;
import model.db.daos.EmployeDAO;
import utils.Util;

import javax.servlet.ServletRequest;
import java.sql.Date;
import java.text.ParseException;

public class EmployeManager {
    private final EmployeDAO employeDAO;

    public EmployeManager() {
        this.employeDAO = new EmployeDAO();
    }


}
