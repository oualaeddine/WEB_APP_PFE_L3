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
    public LinkedList<HashMap<Month, Integer>> visitesPrevuesVariaton() {
        return null;
    }

    // TODO: 5/4/2018
    public LinkedList<HashMap<Month, Integer>> visitesReporteesVariaton() {
        return null;
    }

    // TODO: 5/4/2018
    public LinkedList<HashMap<Month, Integer>> visitesAnnuleesVariaton() {
        return null;
    }

    // TODO: 5/4/2018
    public int visitesNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public LinkedList<HashMap<Localite, Integer>> VisitesNbrPerRegion() {
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
