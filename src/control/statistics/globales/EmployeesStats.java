package control.statistics.globales;

import control.statistics.Stats;
import model.db.daos.ClientDAO;

public class EmployeesStats extends Stats {

    public EmployeesStats() {
        dao = new ClientDAO();
    }
}
