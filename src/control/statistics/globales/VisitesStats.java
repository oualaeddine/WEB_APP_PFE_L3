package control.statistics.globales;

import control.statistics.Stats;
import model.db.daos.VisitesDao;

import java.time.Month;
import java.util.HashMap;
import java.util.LinkedList;

public class VisitesStats extends Stats {
    public VisitesStats() {
        dao = new VisitesDao();
    }

    public int visitesPrevuesNbr() {
        return 0;
    }

    public int visitesReporteesNbr() {
        return 0;
    }

    public int visitesAnnuleesNbr() {
        return 0;
    }

    public LinkedList<HashMap<Month, Integer>> visitesPrevuesVariaton() {
        return null;
    }

    public LinkedList<HashMap<Month, Integer>> visitesReporteesVariaton() {
        return null;
    }

    public LinkedList<HashMap<Month, Integer>> visitesAnnuleesVariaton() {
        return null;
    }
}
