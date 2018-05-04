package control.statistics.globales;

import control.statistics.Stats;
import model.beans.Localite;
import model.db.daos.VentesDAO;

import java.time.Month;
import java.util.HashMap;

public class VentesStats extends Stats {

    public VentesStats() {
        dao = new VentesDAO();
    }

    // TODO: 5/4/2018
    public int ventesNbr() {
        return dao.countAll();
    }

    // TODO: 5/4/2018
    public int confirmedVentesNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int pendingVentesNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int canceledVentesNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public HashMap<Month, Integer> confirmedVentesVariaton() {
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
    public HashMap<Month, Integer> pendingVentesVariaton() {
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
    public HashMap<Month, Integer> canceledVentesVariaton() {
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
    public HashMap<Localite, Integer> ventesNbrPerRegion() {
        return null;
    }
}
