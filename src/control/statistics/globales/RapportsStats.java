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

    public int rapportsNbr() {
        return dao.countAll();
    }

    public int absencesNbr() {
        return dao.getAbsencesNbr();
    }

    public int presencesNbr() {
        return dao.getPresences();
    }

    public int positifsNbr() {
        return dao.getVisitesPositivesNbr();
    }

    public int negatifsNbr() {
        return dao.getVisitesNegativesNbr();
    }

    public float positifPercentage() {
        return dao.pourcentageAvisPositifs();
    }

    public float negatifPercentage() {
        return dao.pourcentageAvisNegatifs();
    }

    public float absencesPercentage() {
        return dao.pourcentageAbsences();
    }

    public float presencesPercentage() {
        return dao.pourcentagePresences();
    }

    /**
     * variations
     **/
    public HashMap<Month, Integer> absencesVariation() {
        HashMap<Month, Integer> variation = new HashMap<>();

        variation.put(Month.JANUARY, dao.getAbsencesForMonth(Month.JANUARY));
        variation.put(Month.FEBRUARY, dao.getAbsencesForMonth(Month.FEBRUARY));
        variation.put(Month.MARCH, dao.getAbsencesForMonth(Month.MARCH));
        variation.put(Month.APRIL, dao.getAbsencesForMonth(Month.APRIL));
        variation.put(Month.MAY, dao.getAbsencesForMonth(Month.MAY));
        variation.put(Month.JUNE, dao.getAbsencesForMonth(Month.JUNE));
        variation.put(Month.JULY, dao.getAbsencesForMonth(Month.JULY));
        variation.put(Month.AUGUST, dao.getAbsencesForMonth(Month.AUGUST));
        variation.put(Month.SEPTEMBER, dao.getAbsencesForMonth(Month.SEPTEMBER));
        variation.put(Month.OCTOBER, dao.getAbsencesForMonth(Month.OCTOBER));
        variation.put(Month.NOVEMBER, dao.getAbsencesForMonth(Month.NOVEMBER));
        variation.put(Month.DECEMBER, dao.getAbsencesForMonth(Month.DECEMBER));

        return variation;
    }

    public HashMap<Month, Integer> presencesVariation() {
        HashMap<Month, Integer> variation = new HashMap<>();

        variation.put(Month.JANUARY, dao.getPresencesForMonth(Month.JANUARY));
        variation.put(Month.FEBRUARY, dao.getPresencesForMonth(Month.FEBRUARY));
        variation.put(Month.MARCH, dao.getPresencesForMonth(Month.MARCH));
        variation.put(Month.APRIL, dao.getPresencesForMonth(Month.APRIL));
        variation.put(Month.MAY, dao.getPresencesForMonth(Month.MAY));
        variation.put(Month.JUNE, dao.getPresencesForMonth(Month.JUNE));
        variation.put(Month.JULY, dao.getPresencesForMonth(Month.JULY));
        variation.put(Month.AUGUST, dao.getPresencesForMonth(Month.AUGUST));
        variation.put(Month.SEPTEMBER, dao.getPresencesForMonth(Month.SEPTEMBER));
        variation.put(Month.OCTOBER, dao.getPresencesForMonth(Month.OCTOBER));
        variation.put(Month.NOVEMBER, dao.getPresencesForMonth(Month.NOVEMBER));
        variation.put(Month.DECEMBER, dao.getPresencesForMonth(Month.DECEMBER));

        return variation;
    }

    public HashMap<Month, Integer> positifsVariation() {
        HashMap<Month, Integer> variation = new HashMap<>();

        variation.put(Month.JANUARY, dao.getAvisPositifsForMonth(Month.JANUARY));
        variation.put(Month.FEBRUARY, dao.getAvisPositifsForMonth(Month.FEBRUARY));
        variation.put(Month.MARCH, dao.getAvisPositifsForMonth(Month.MARCH));
        variation.put(Month.APRIL, dao.getAvisPositifsForMonth(Month.APRIL));
        variation.put(Month.MAY, dao.getAvisPositifsForMonth(Month.MAY));
        variation.put(Month.JUNE, dao.getAvisPositifsForMonth(Month.JUNE));
        variation.put(Month.JULY, dao.getAvisPositifsForMonth(Month.JULY));
        variation.put(Month.AUGUST, dao.getAvisPositifsForMonth(Month.AUGUST));
        variation.put(Month.SEPTEMBER, dao.getAvisPositifsForMonth(Month.SEPTEMBER));
        variation.put(Month.OCTOBER, dao.getAvisPositifsForMonth(Month.OCTOBER));
        variation.put(Month.NOVEMBER, dao.getAvisPositifsForMonth(Month.NOVEMBER));
        variation.put(Month.DECEMBER, dao.getAvisPositifsForMonth(Month.DECEMBER));

        return variation;
    }

    public HashMap<Month, Integer> negatifsVariation() {
        HashMap<Month, Integer> variation = new HashMap<>();

        variation.put(Month.JANUARY, dao.getAvisNegatifsForMonth(Month.JANUARY));
        variation.put(Month.FEBRUARY, dao.getAvisNegatifsForMonth(Month.FEBRUARY));
        variation.put(Month.MARCH, dao.getAvisNegatifsForMonth(Month.MARCH));
        variation.put(Month.APRIL, dao.getAvisNegatifsForMonth(Month.APRIL));
        variation.put(Month.MAY, dao.getAvisNegatifsForMonth(Month.MAY));
        variation.put(Month.JUNE, dao.getAvisNegatifsForMonth(Month.JUNE));
        variation.put(Month.JULY, dao.getAvisNegatifsForMonth(Month.JULY));
        variation.put(Month.AUGUST, dao.getAvisNegatifsForMonth(Month.AUGUST));
        variation.put(Month.SEPTEMBER, dao.getAvisNegatifsForMonth(Month.SEPTEMBER));
        variation.put(Month.OCTOBER, dao.getAvisNegatifsForMonth(Month.OCTOBER));
        variation.put(Month.NOVEMBER, dao.getAvisNegatifsForMonth(Month.NOVEMBER));
        variation.put(Month.DECEMBER, dao.getAvisNegatifsForMonth(Month.DECEMBER));

        return variation;
    }

}
