package control.statistics.globales;

import control.statistics.Stats;
import model.beans.Localite;
import model.db.daos.LogementDAO;

import java.util.HashMap;
import java.util.LinkedList;

public class LogementsStats extends Stats {
    public LogementsStats() {
        dao = new LogementDAO();
    }

    // TODO: 5/4/2018
    public int logementsNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public LinkedList<HashMap<Localite, Integer>> logementsNbrPerRegion() {
        return null;
    }

    // TODO: 5/4/2018
    public int logementsVendusNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public LinkedList<HashMap<Localite, Integer>> logementsVendusNbrPerRegion() {
        return null;
    }

    // TODO: 5/4/2018
    public int logementsGeleNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public LinkedList<HashMap<Localite, Integer>> logementsGeleNbrPerRegion() {
        return null;
    }

}
