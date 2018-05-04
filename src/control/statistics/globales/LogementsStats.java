package control.statistics.globales;

import control.statistics.Stats;
import model.beans.Localite;
import model.db.daos.LogementDAO;

import java.time.Month;
import java.util.HashMap;
import java.util.LinkedList;

public class LogementsStats extends Stats {
    public LogementsStats() {
        dao = new LogementDAO();
    }

    public int logementsNbr() {
        return dao.countAll();
    }

    // TODO: 5/4/2018
    public LinkedList<HashMap<Localite, Integer>> logementsNbrPerRegion() {
        return null;
    }

    public int logementsVendusNbr() {

        return new LogementDAO().getNbrVendus();
    }

    // TODO: 5/4/2018
    public HashMap<Month, Integer> logementsVendusNbrVariation() {

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
    public HashMap<Localite, Integer> logementsVendusNbrPerRegion() {
        return null;
    }

    public int logementsGeleNbr() {
        return new LogementDAO().getNbrGeles();
    }

    // TODO: 5/4/2018
    public LinkedList<HashMap<Localite, Integer>> logementsGeleNbrPerRegion() {
        return null;
    }

}
