package control.statistics.perso;

import control.statistics.PersoStats;
import model.db.daos.*;
import model.enums.UserType;

import java.time.Month;
import java.util.HashMap;

@SuppressWarnings("Duplicates")
public class AgentStats extends PersoStats {

    private final VisitesDao visitesDao;
    private final EmployeDAO employeeDao;
    private final RapportDAO rapportsDao;

    public AgentStats(int userId) {
        super(userId);
        this.visitesDao = new VisitesDao();
        this.employeeDao = new EmployeDAO();
        this.rapportsDao = new RapportDAO();
    }

    /**
     * @return the number of unread messages
     */
    public int getNewMessagesNbr() {// TODO: 5/3/2018
        return 0;
    }

    public int getReportedVisites() {
        return employeeDao.myReportedVisitsNbr(userId);
    }


    public int getCanceledVisites() {
        return employeeDao.myCanceledVisitsNbr(userId);
    }


    public int getProgrammedVisites() {
        return employeeDao.myProgrammedVisitsNbr(userId);
    }

    /**
     * @return the number of upcoming canceled visits
     */
    public int getVisitedLogementsNbr() {
        return employeeDao.myVisitedLogementsNbr(userId);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Rapports
     **/
    public int rapportsNbr() {
        return rapportsDao.nbrRapportsForAgent(userId);
    }

    public int absencesNbr() {
        return rapportsDao.getAbsencesNbrForAgent(userId);
    }

    public int presencesNbr() {
        return rapportsDao.getPresencesNbrForAgent(userId);
    }

    public int positifsNbr() {
        return rapportsDao.getAvisPositifsForAgent(userId);
    }

    public int negatifsNbr() {
        return rapportsDao.getAvisNegatifsForAgent(userId);
    }

    public float positifPercentage() {
        return rapportsDao.pourcentagePositifForAgent(userId);
    }

    public float negatifPercentage() {
        return rapportsDao.pourcentageNegatifForAgent(userId);
    }

    public float absencesPercentage() {
        return rapportsDao.pourcentageAbsenceForAgent(userId);
    }

    public float presencesPercentage() {
        return rapportsDao.pourcentagePresenceForAgent(userId);
    }

    /**
     * variations
     **/
    // TODO: 5/4/2018
    public HashMap<Month, Integer> absencesVariation() {
        HashMap<Month, Integer> variation = new HashMap<>();

        variation.put(Month.JANUARY, 0);
        variation.put(Month.FEBRUARY, 0);
        variation.put(Month.MARCH, 0);
        variation.put(Month.APRIL, 0);
        variation.put(Month.MAY, 0);
        variation.put(Month.JUNE, 0);
        variation.put(Month.JULY, 0);
        variation.put(Month.AUGUST, 0);
        variation.put(Month.SEPTEMBER, 0);
        variation.put(Month.OCTOBER, 0);
        variation.put(Month.NOVEMBER, 0);
        variation.put(Month.DECEMBER, 0);

        return variation;
    }

    // TODO: 5/4/2018
    public HashMap<Month, Integer> presencesVariation() {
        HashMap<Month, Integer> variation = new HashMap<>();

        variation.put(Month.JANUARY, rapportsDao.getPresencesForMonth(Month.JANUARY));
        variation.put(Month.FEBRUARY, 0);
        variation.put(Month.MARCH, 0);
        variation.put(Month.APRIL, 0);
        variation.put(Month.MAY, 0);
        variation.put(Month.JUNE, 0);
        variation.put(Month.JULY, 0);
        variation.put(Month.AUGUST, 0);
        variation.put(Month.SEPTEMBER, 0);
        variation.put(Month.OCTOBER, 0);
        variation.put(Month.NOVEMBER, 0);
        variation.put(Month.DECEMBER, 0);

        return variation;
    }

    // TODO: 5/4/2018
    public HashMap<Month, Integer> positifsVariation() {
        HashMap<Month, Integer> variation = new HashMap<>();
// TODO: 5/4/2018 f dao ndiro method traj3alna haja kima haka , sinon pour chaque month ndirou counter f dao

        variation.put(Month.JANUARY, 0);
        variation.put(Month.FEBRUARY, 0);
        variation.put(Month.MARCH, 0);
        variation.put(Month.APRIL, 0);
        variation.put(Month.MAY, 0);
        variation.put(Month.JUNE, 0);
        variation.put(Month.JULY, 0);
        variation.put(Month.AUGUST, 0);
        variation.put(Month.SEPTEMBER, 0);
        variation.put(Month.OCTOBER, 0);
        variation.put(Month.NOVEMBER, 0);
        variation.put(Month.DECEMBER, 0);

        return variation;
    }

    // TODO: 5/4/2018
    public HashMap<Month, Integer> negatifsVariation() {
        HashMap<Month, Integer> variation = new HashMap<>();
// TODO: 5/4/2018 f dao ndiro method traj3alna haja kima haka , sinon pour chaque month ndirou counter f dao

        variation.put(Month.JANUARY, 0);
        variation.put(Month.FEBRUARY, 0);
        variation.put(Month.MARCH, 0);
        variation.put(Month.APRIL, 0);
        variation.put(Month.MAY, 0);
        variation.put(Month.JUNE, 0);
        variation.put(Month.JULY, 0);
        variation.put(Month.AUGUST, 0);
        variation.put(Month.SEPTEMBER, 0);
        variation.put(Month.OCTOBER, 0);
        variation.put(Month.NOVEMBER, 0);
        variation.put(Month.DECEMBER, 0);

        return variation;
    }


    public int getClients() {
        return new ClientDAO().getClientsForAgent(userId).size();
    }

    public float clientsPercentage() {

        return new ClientDAO().percentageClientsForAgent(userId);
    }

    public float logementsVisitesPercentage() {
        int logementsVisites = new LogementDAO().getLogementsForAgent(userId).size();
        int all = new LogementDAO().countAll();
        return logementsVisites * 100 / all;
    }

}
