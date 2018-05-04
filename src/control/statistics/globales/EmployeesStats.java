package control.statistics.globales;

import control.statistics.Stats;
import model.db.daos.EmployeDAO;

public class EmployeesStats extends Stats {

    public EmployeesStats() {
        dao = new EmployeDAO();
    }

    // TODO: 5/4/2018
    public int employeesNbr() {
        //dao.getEmployeesNbr();
        return 0;
    }

    // TODO: 5/4/2018
    public int agentsNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int operateursNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int responsablesVentesNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int adminsNbr() {
        return 0;
    }

}
