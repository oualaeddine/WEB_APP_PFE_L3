package control.statistics.globales;

import control.statistics.Stats;
import model.db.daos.RapportDAO;

import java.time.Month;
import java.util.HashMap;

@SuppressWarnings("Duplicates")
public class RapportsStats extends Stats {

    private final RapportDAO dao;

    public RapportsStats() {
        dao = new RapportDAO();
    }

    // TODO: 5/4/2018
    public int rapportsNbr() {
        return dao.countAll();
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

        variation.put(Month.JANUARY, dao.getPresencesForMonth(Month.JANUARY));
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
