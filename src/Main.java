import model.beans.RDV;
import model.beans.Vente;
import model.beans.humans.Client;
import model.db.daos.VentesDAO;
import model.db.daos.VisitesDao;

import java.sql.Date;
import java.util.LinkedList;


public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        client.setId(3);
        LinkedList<Vente> ventes = new VentesDAO().getEnCoursForClient(client);
        for (Vente vente : ventes) {
            System.out.println("vente: " + vente.getId());
        }
    }
}
