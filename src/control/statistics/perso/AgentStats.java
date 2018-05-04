package control.statistics.perso;

import control.statistics.PersoStats;
import model.db.daos.EmployeDAO;
import model.db.daos.RapportDAO;
import model.db.daos.VisitesDao;

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

    /**
     * @return the number of upcoming reported visits
     */
    public int getReportedVisites() {// TODO: 5/3/2018
        return 0;
    }

    /**
     * @return the number of upcoming reported visits
     */
    public int getCanceledVisites() {// TODO: 5/3/2018
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
    public HashMap<Month, Integer> absencesVariation() {
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


}
