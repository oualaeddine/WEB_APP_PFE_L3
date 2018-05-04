package control.statistics.globales;

import model.db.daos.EmployeDAO;
import model.enums.UserType;

public class EmployeesStats {
    private EmployeDAO dao;

    public EmployeesStats() {
        dao = new EmployeDAO();
    }

    public int employeesNbr() {
        return dao.countAll();
    }

    public int agentsNbr() {
        return dao.countEmployeeByType(UserType.AGENT);
    }

    public int notApprouvedAgentsNbr() {

        return dao.notApprovedNbrByType(UserType.AGENT);
    }

    public int operateursNbr() {
        return dao.countEmployeeByType(UserType.OPERATEUR);
    }

    public int notApprouvedOperateursNbr() {
        return dao.notApprovedNbrByType(UserType.OPERATEUR);
    }

    public int responsablesVentesNbr() {
        return dao.countEmployeeByType(UserType.RESPONSABLE_VENTES);
    }

    public int notApprouvedResponsablesVentesNbr() {
        return dao.notApprovedNbrByType(UserType.RESPONSABLE_VENTES);
    }

    public int adminsNbr() {
        return dao.countEmployeeByType(UserType.ADMIN);
    }

    public int notApprouvedAdminsNbr() {
        return dao.notApprovedNbrByType(UserType.ADMIN);
    }

    public int superUsersNbr() {
        return dao.countEmployeeByType(UserType.SU);
    }

    public int notApprovedSuperUsers() {
        return dao.notApprovedNbrByType(UserType.SU);
    }

}
