package control.system.managers;

import model.db.daos.EmployeDAO;

public class EmployeManager {
    private final EmployeDAO employeDAO;

    public EmployeManager() {
        this.employeDAO = new EmployeDAO();
    }


}
