package control.statistics.perso;

import control.statistics.PersoStats;
import model.beans.humans.Client;
import model.db.daos.ClientDAO;
import model.db.daos.VentesDAO;

public class ClientStats extends PersoStats {

    public ClientStats(int userId) {
        super(userId);
    }



    /**
     * @return the number of upcoming reported visits
     */
    public int getReportedVisites() {// TODO: 5/3/2018
        return 0;
    }

    /**
     * @return the number of upcoming programmed visits
     */
    public int getProgrammedVisites() {// TODO: 5/3/2018
        return 0;
    }

    /**
     * @return the number of upcoming canceled visits
     */
    public int getVisitedLogementsNbr() {// TODO: 5/3/2018
        return new ClientDAO().visitedLogementsNbrForClient(userId);
    }

    /**
     * @return the number of upcoming canceled visits
     */
    public int getVentesNbr(int client) {
        Client client1 = new Client();
        client1.setId(userId);
        return new VentesDAO().getByClient(client1).size();
    }

    // TODO: 5/4/2018
    public int ventesNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int confirmedVentesNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int pendingVentesNbr() {
        return 0;
    }

    // TODO: 5/4/2018
    public int canceledVentesNbr() {
        return 0;
    }


}
