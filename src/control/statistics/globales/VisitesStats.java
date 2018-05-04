package control.statistics.globales;

import control.statistics.Stats;
import model.beans.Localite;
import model.db.daos.VisitesDao;

import java.time.Month;
import java.util.HashMap;

public class VisitesStats extends Stats {
    public VisitesStats() {
        dao = new VisitesDao();
    }


    // TODO: 5/4/2018
    public int visitesPrevuesNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int visitesReporteesNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int visitesAnnuleesNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public HashMap<Month, Integer> visitesPrevuesVariaton() {
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
    public HashMap<Month, Integer> visitesReporteesVariaton() {
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
    public HashMap<Month, Integer> visitesAnnuleesVariaton() {
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
    public int visitesNbr() {
        return dao.countAll();
    }

    // TODO: 5/4/2018
    public HashMap<Localite, Integer> VisitesNbrPerRegion() {
        return null;
    }

    // TODO: 5/4/2018
    public Localite regionLaPlusVisitee() {
        return null;
    }

    // TODO: 5/4/2018
    public Localite regionLaMoinVisitee() {
        return null;
    }
}
