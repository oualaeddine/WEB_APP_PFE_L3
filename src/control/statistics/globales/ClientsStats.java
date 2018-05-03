package control.statistics.globales;

import control.statistics.Stats;
import model.db.daos.ClientDAO;

public class ClientsStats extends Stats {
    public ClientsStats() {
        dao = new ClientDAO();
    }

    public int clientsNb() {
        return 0;
    }
}
