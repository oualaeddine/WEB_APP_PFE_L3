package control.statistics.globales;

import control.statistics.Stats;
import model.beans.Localite;
import model.db.daos.VisitesDao;

import java.time.Month;
import java.util.HashMap;
import java.util.LinkedList;

public class VisitesStats extends Stats {
    public VisitesStats() {
        dao = new VisitesDao();
    }


    public int visitesPrevuesNbr() {
        return new VisitesDao().nbrVisitesPrevues();
    }

    public int visitesReporteesNbr() {
        return new VisitesDao().nbrVisitesReportees();
    }

    public int visitesAnnuleesNbr() {
        return new VisitesDao().nbrVisitesAnnulees();
    }

    public int allVisitesByMonth(Month month) {
        return new VisitesDao().nbrVisitesForMonth(month);
    }

    public HashMap<Month, Integer> visitesPrevuesVariaton() {
        HashMap<Month, Integer> variation = new HashMap<>();

        variation.put(Month.JANUARY, new VisitesDao().nbrVisitesPrevuesForMonth(Month.JANUARY));
        variation.put(Month.FEBRUARY, new VisitesDao().nbrVisitesPrevuesForMonth(Month.FEBRUARY));
        variation.put(Month.MARCH, new VisitesDao().nbrVisitesPrevuesForMonth(Month.MARCH));
        variation.put(Month.APRIL, new VisitesDao().nbrVisitesPrevuesForMonth(Month.APRIL));
        variation.put(Month.MAY, new VisitesDao().nbrVisitesPrevuesForMonth(Month.MAY));
        variation.put(Month.JUNE, new VisitesDao().nbrVisitesPrevuesForMonth(Month.JUNE));
        variation.put(Month.JULY, new VisitesDao().nbrVisitesPrevuesForMonth(Month.JULY));
        variation.put(Month.AUGUST, new VisitesDao().nbrVisitesPrevuesForMonth(Month.AUGUST));
        variation.put(Month.SEPTEMBER, new VisitesDao().nbrVisitesPrevuesForMonth(Month.SEPTEMBER));
        variation.put(Month.OCTOBER, new VisitesDao().nbrVisitesPrevuesForMonth(Month.OCTOBER));
        variation.put(Month.NOVEMBER, new VisitesDao().nbrVisitesPrevuesForMonth(Month.NOVEMBER));
        variation.put(Month.DECEMBER, new VisitesDao().nbrVisitesPrevuesForMonth(Month.DECEMBER));

        return variation;
    }

    public HashMap<Month, Integer> visitesReporteesVariaton() {
        HashMap<Month, Integer> variation = new HashMap<>();

        variation.put(Month.JANUARY, new VisitesDao().nbrVisitesReporteesForMonth(Month.JANUARY));
        variation.put(Month.FEBRUARY, new VisitesDao().nbrVisitesReporteesForMonth(Month.FEBRUARY));
        variation.put(Month.MARCH, new VisitesDao().nbrVisitesReporteesForMonth(Month.MARCH));
        variation.put(Month.APRIL, new VisitesDao().nbrVisitesReporteesForMonth(Month.APRIL));
        variation.put(Month.MAY, new VisitesDao().nbrVisitesReporteesForMonth(Month.MAY));
        variation.put(Month.JUNE, new VisitesDao().nbrVisitesReporteesForMonth(Month.JUNE));
        variation.put(Month.JULY, new VisitesDao().nbrVisitesReporteesForMonth(Month.JULY));
        variation.put(Month.AUGUST, new VisitesDao().nbrVisitesReporteesForMonth(Month.AUGUST));
        variation.put(Month.SEPTEMBER, new VisitesDao().nbrVisitesReporteesForMonth(Month.SEPTEMBER));
        variation.put(Month.OCTOBER, new VisitesDao().nbrVisitesReporteesForMonth(Month.OCTOBER));
        variation.put(Month.NOVEMBER, new VisitesDao().nbrVisitesReporteesForMonth(Month.NOVEMBER));
        variation.put(Month.DECEMBER, new VisitesDao().nbrVisitesReporteesForMonth(Month.DECEMBER));

        return variation;
    }

    public HashMap<Month, Integer> visitesAnnuleesVariaton() {
        HashMap<Month, Integer> variation = new HashMap<>();

        variation.put(Month.JANUARY, new VisitesDao().nbrVisitesAnnuleesForMonth(Month.JANUARY));
        variation.put(Month.FEBRUARY, new VisitesDao().nbrVisitesAnnuleesForMonth(Month.FEBRUARY));
        variation.put(Month.MARCH, new VisitesDao().nbrVisitesAnnuleesForMonth(Month.MARCH));
        variation.put(Month.APRIL, new VisitesDao().nbrVisitesAnnuleesForMonth(Month.APRIL));
        variation.put(Month.MAY, new VisitesDao().nbrVisitesAnnuleesForMonth(Month.MAY));
        variation.put(Month.JUNE, new VisitesDao().nbrVisitesAnnuleesForMonth(Month.JUNE));
        variation.put(Month.JULY, new VisitesDao().nbrVisitesAnnuleesForMonth(Month.JULY));
        variation.put(Month.AUGUST, new VisitesDao().nbrVisitesAnnuleesForMonth(Month.AUGUST));
        variation.put(Month.SEPTEMBER, new VisitesDao().nbrVisitesAnnuleesForMonth(Month.SEPTEMBER));
        variation.put(Month.OCTOBER, new VisitesDao().nbrVisitesAnnuleesForMonth(Month.OCTOBER));
        variation.put(Month.NOVEMBER, new VisitesDao().nbrVisitesAnnuleesForMonth(Month.NOVEMBER));
        variation.put(Month.DECEMBER, new VisitesDao().nbrVisitesAnnuleesForMonth(Month.DECEMBER));

        return variation;
    }

    public int visitesNbr() {
        return dao.countAll();
    }

    public HashMap<Localite, Integer> VisitesNbrPerRegion() {
        HashMap<Localite, Integer> variation = new HashMap<>();
        LinkedList<Localite> localites = new LinkedList<>();
        for (Localite localite : localites) {
            variation.put(localite, new VisitesDao().nbrVisitesParRegion(localite.getId()));
        }
        return variation;
    }

    public Localite regionLaPlusVisitee() {
        return new VisitesDao().getMostVisitedRegion();
    }

    public LinkedList<Localite> top5Regions() {
        return new VisitesDao().getTopFiveRegions();
    }

    // TODO: 5/4/2018
    public Localite regionLaMoinVisitee() {
        return null;
    }
}
