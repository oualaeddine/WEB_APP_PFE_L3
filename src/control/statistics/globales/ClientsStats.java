package control.statistics.globales;

import control.statistics.Stats;
import model.db.daos.ClientDAO;

import java.time.Month;
import java.util.HashMap;

public class ClientsStats extends Stats {
    public ClientsStats() {
        dao = new ClientDAO();
    }

    // TODO: 5/4/2018
    public int clientsNbr() {
        return dao.countAll();
    }

    // TODO: 5/4/2018
    public int newClientsThisMonthNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public HashMap<Month, Integer> newClientsVariation() {
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

    // TODO: 5/4/2018 (max ventes)
    public int bestClient() {
        return 0;
    }


}
