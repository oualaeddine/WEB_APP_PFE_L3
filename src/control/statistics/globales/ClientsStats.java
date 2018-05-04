package control.statistics.globales;

import control.statistics.Stats;
import model.db.daos.ClientDAO;

public class ClientsStats extends Stats {
    public ClientsStats() {
        dao = new ClientDAO();
    }

    // TODO: 5/4/2018
    public int clientsNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int newClientsThisMonthNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int newClientsVariation() {
        return 0;
    }
}
