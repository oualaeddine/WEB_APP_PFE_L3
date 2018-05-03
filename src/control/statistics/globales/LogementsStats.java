package control.statistics.globales;

import control.statistics.Stats;
import model.db.daos.LogementDAO;

public class LogementsStats extends Stats {
    public LogementsStats() {
        dao = new LogementDAO();
    }
}
