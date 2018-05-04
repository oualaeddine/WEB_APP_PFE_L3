package control.statistics.perso;

import control.statistics.globales.EmployeesStats;
import model.db.daos.EmployeDAO;
import model.db.daos.VisitesDao;

public class AgentStats extends EmployeesStats {

    private final int agentId;
    private final VisitesDao visitesDao;
    private final EmployeDAO employeeDao;

    public AgentStats(int userId) {
        super();
        this.agentId = userId;
        this.visitesDao = new VisitesDao();
        this.employeeDao = (EmployeDAO) dao;
    }

    /**
     * @return the number of unread messages
     */
    public int getNewMessagesNbr() {// TODO: 5/3/2018
        return 0;
    }

    /**
     * @return the number of upcoming reported visits
     */
    public int getReportedVisites() {// TODO: 5/3/2018
        return 0;
    }

    /**
     * @return the number of upcoming programmed visits
     */
    public int getProgrammedVisites() {// TODO: 5/3/2018
        return 0;
    }

    /**
     * @return the number of upcoming canceled visits
     */
    public int getVisitedLogementsNbr() {// TODO: 5/3/2018
        return 0;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Rapports
     **/
    // TODO: 5/4/2018
    public int rapportsNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int absencesNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int presencesNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int positifsNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int negatifsNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public float positifPercentage() {
        return 0;
    }

    // TODO: 5/4/2018
    public float negatifPercentage() {
        return 0;
    }

    // TODO: 5/4/2018
    public float abcencesPercentage() {
        return 0;
    }

    // TODO: 5/4/2018
    public float presencesPercentage() {
        return 0;
    }

    /**
     * variations
     **/
    // TODO: 5/4/2018
    public int absencesVariation() {
        return 0;
    }

    // TODO: 5/4/2018
    public int presencesVariation() {
        return 0;
    }

    // TODO: 5/4/2018
    public int positifsVariation() {
        return 0;
    }

    // TODO: 5/4/2018
    public int negatifsVariation() {
        return 0;
    }


}
