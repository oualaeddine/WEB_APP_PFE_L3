package control.statistics.globales;

import control.statistics.Stats;
import model.db.daos.ClientDAO;

import java.time.Month;
import java.util.Calendar;
import java.util.HashMap;

public class ClientsStats extends Stats {
    public ClientsStats() {
        dao = new ClientDAO();
    }

    public int clientsNbr() {
        return dao.countAll();
    }

    public int newClientsThisMonthNbr() {
        return new ClientDAO().getNewClientsByMonth(Calendar.getInstance().get(Calendar.MONTH));
    }

    public HashMap<Month, Integer> newClientsVariation() {
        HashMap<Month, Integer> variation = new HashMap<>();
        variation.put(Month.JANUARY, new ClientDAO().getNewClientsByMonth(Month.JANUARY.getValue()));
        variation.put(Month.FEBRUARY, new ClientDAO().getNewClientsByMonth(Month.FEBRUARY.getValue()));
        variation.put(Month.MARCH, new ClientDAO().getNewClientsByMonth(Month.MARCH.getValue()));
        variation.put(Month.APRIL, new ClientDAO().getNewClientsByMonth(Month.APRIL.getValue()));
        variation.put(Month.MAY, new ClientDAO().getNewClientsByMonth(Month.MAY.getValue()));
        variation.put(Month.JUNE, new ClientDAO().getNewClientsByMonth(Month.JUNE.getValue()));
        variation.put(Month.JULY, new ClientDAO().getNewClientsByMonth(Month.JULY.getValue()));
        variation.put(Month.AUGUST, new ClientDAO().getNewClientsByMonth(Month.AUGUST.getValue()));
        variation.put(Month.SEPTEMBER, new ClientDAO().getNewClientsByMonth(Month.SEPTEMBER.getValue()));
        variation.put(Month.OCTOBER, new ClientDAO().getNewClientsByMonth(Month.OCTOBER.getValue()));
        variation.put(Month.NOVEMBER, new ClientDAO().getNewClientsByMonth(Month.NOVEMBER.getValue()));
        variation.put(Month.DECEMBER, new ClientDAO().getNewClientsByMonth(Month.DECEMBER.getValue()));

        return variation;
    }

    public int bestClient() {
        return new ClientDAO().getBestClient();
    }


}
