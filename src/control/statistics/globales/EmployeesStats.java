package control.statistics.globales;

import model.db.daos.EmployeDAO;
import model.enums.UserType;

public class EmployeesStats {
    private EmployeDAO dao;

    public EmployeesStats() {
        dao = new EmployeDAO();
    }

    // TODO: 5/4/2018
    public int employeesNbr() {
        return dao.countAll();
    }

    // TODO: 5/4/2018
    public int agentsNbr() {
        return dao.countEmployeeByType(UserType.AGENT);
    }

    // TODO: 5/4/2018
    public int notApprouvedAgentsNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int operateursNbr() {
        return dao.countEmployeeByType(UserType.OPERATEUR);
    }

    // TODO: 5/4/2018
    public int notApprouvedOperateursNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int responsablesVentesNbr() {
        return dao.countEmployeeByType(UserType.RESPONSABLE_VENTES);
    }

    // TODO: 5/4/2018
    public int notApprouvedResponsablesVentesNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int adminsNbr() {
        return dao.countEmployeeByType(UserType.ADMIN);
    }

    // TODO: 5/4/2018
    public int notApprouvedAdminsNbr() {
        return 0;
    }

}
