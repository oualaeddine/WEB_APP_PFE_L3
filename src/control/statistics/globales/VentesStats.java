package control.statistics.globales;

import control.statistics.Stats;
import model.db.daos.VentesDAO;

import java.time.Month;
import java.util.HashMap;
import java.util.LinkedList;

public class VentesStats extends Stats {

    public VentesStats() {
        dao = new VentesDAO();
    }

    public int confirmedVentesNbr() {
        return 0;
    }

    public int pendingVentesNbr() {
        return 0;
    }

    public int canceledVentesNbr() {
        return 0;
    }

    public LinkedList<HashMap<Month, Integer>> confirmedVentesVariaton() {
        return null;
    }

    public LinkedList<HashMap<Month, Integer>> pendingVentesVariaton() {
        return null;
    }

    public LinkedList<HashMap<Month, Integer>> canceledVentesVariaton() {
        return null;
    }

}
